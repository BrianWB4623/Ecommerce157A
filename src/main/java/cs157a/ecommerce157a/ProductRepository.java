package cs157a.ecommerce157a;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> findAll() {
        String sql = """
            SELECT PRODUCT_ID, NAME, MANF, CATEGORY,
                   DESCRIPTION, IMAGE, PRICE
            FROM Products
            """;

        return jdbc.query(sql, (rs, rowNum) -> new Product(
            rs.getInt("PRODUCT_ID"),
            rs.getString("NAME"),
            rs.getString("MANF"),
            rs.getString("CATEGORY"),
            rs.getString("DESCRIPTION"),
            rs.getBytes("IMAGE"),
            rs.getBigDecimal("PRICE")
        ));
    }

    public void insert(Product product) {
        String sql = """
            INSERT INTO Products
                (NAME, MANF, CATEGORY, DESCRIPTION, IMAGE, PRICE)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        jdbc.update(
            sql,
            product.name(),
            product.manf(),
            product.category(),
            product.description(),
            product.image(),
            product.price()
        );
    }

    public int deleteById(Integer id) {
    return jdbc.update(
        "DELETE FROM Products WHERE PRODUCT_ID = ?",
        id
    );
    }   
}