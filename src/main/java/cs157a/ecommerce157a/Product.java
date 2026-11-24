package cs157a.ecommerce157a;

import java.math.BigDecimal;

public record Product(
    Integer productId,
    String name,
    String manf,
    String category,
    String description,
    byte[] image,
    BigDecimal price
){}