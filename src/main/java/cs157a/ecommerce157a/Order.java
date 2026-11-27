package cs157a.ecommerce157a;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Order(
    Integer customerId,
    Integer shippingId,
    LocalDateTime orderDate,
    BigDecimal totalAmount,
    Status orderStatus
){}

enum Status{
    UNFULFILLED,
    FULLFILLED,
    CANCELLED
}