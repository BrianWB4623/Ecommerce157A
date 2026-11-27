package cs157a.ecommerce157a;

import java.math.BigDecimal;

public record OrderDetails (
    Integer orderId,
    Integer productId,
    Integer quantity,
    BigDecimal purchasePrice
){}
