package cs157a.ecommerce157a;

public record Customer(
    Integer customerId,
    String firstName,
    String lastname,
    String password,
    String email
){}