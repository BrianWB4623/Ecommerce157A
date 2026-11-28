package cs157a.ecommerce157a;

public record Reviews(
    Integer customerId,
    Integer productId,
    Integer starRating,
    String review
){}
