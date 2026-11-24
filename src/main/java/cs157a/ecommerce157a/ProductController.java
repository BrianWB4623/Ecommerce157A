package cs157a.ecommerce157a;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    public record CreateProductRequest(
        String name,
        String manf,
        String category,
        String description,
        byte[] image,
        BigDecimal price
    ) {}

    @GetMapping
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CreateProductRequest request) {
        Product product = new Product(
            null,
            request.name(),
            request.manf(),
            request.category(),
            request.description(),
            request.image(),
            request.price()
        );

        repository.insert(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable("id") Integer id) {

    int deleted = repository.deleteById(id);

    if (deleted == 0) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
    }
}
