package cs157a.ecommerce157a;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {

    private final JdbcTemplate jdbc;

    public InventoryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Inventory> findAll() {
        return jdbc.query(
            "SELECT PRODUCT_ID, IN_STOCK_QUANTITY FROM Inventory",
            (rs, rowNum) -> new Inventory(
                rs.getInt("PRODUCT_ID"),
                rs.getInt("IN_STOCK_QUANTITY")
            )
        );
    }

    public void insert(Inventory inventory) {
        jdbc.update(
            """
            INSERT INTO Inventory (PRODUCT_ID, IN_STOCK_QUANTITY)
            VALUES (?, ?)
            """,
            inventory.productId(),
            inventory.quantity()
        );
    }

    public int updateQuantity(Integer productId, Integer quantity) {
        return jdbc.update(
            """
            UPDATE Inventory
            SET IN_STOCK_QUANTITY = ?
            WHERE PRODUCT_ID = ?
            """,
            quantity,
            productId
        );
    }
}