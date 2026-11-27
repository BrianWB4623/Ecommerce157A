package cs157a.ecommerce157a;

public record Shipping (
    Integer shippingId,
    String address1,
    String address2,
    String city,
    String province,
    String country,
    String zipCode
){}