package cs157a.ecommerce157a;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository repository;

    public InventoryController(InventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Inventory> getInventory() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody Inventory inventory) {
        if (inventory.productId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Missing Product ID"
            );
        }

        validateQuantity(inventory.quantity());

        try {
            repository.insert(inventory);
        } catch (DuplicateKeyException exception) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Inventory already exists for this product",
                exception
            );
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Inventory violates a database constraint; check the product ID",
                exception
            );
        }
    }

    public record QuantityRequest(Integer quantity) {}

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateQuantity(
            @PathVariable("productId") Integer productId,
            @RequestBody QuantityRequest request) {

        validateQuantity(request.quantity());

        int updated = repository.updateQuantity(
            productId,
            request.quantity()
        );

        if (updated == 0) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Inventory not found"
            );
        }
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Quantity must be zero or greater"
            );
        }
    }
}