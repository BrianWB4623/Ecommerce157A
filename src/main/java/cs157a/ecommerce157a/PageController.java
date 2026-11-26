package cs157a.ecommerce157a;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/shop")
    public String products() {
        return "products";
    }

    @GetMapping("/product-details")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/admin/products")
    public String adminProducts() {
        return "admin-products";
    }

    @GetMapping("/admin/orders")
    public String adminOrders() {
        return "admin-orders";
    }

    @GetMapping("/admin/inventory")
    public String adminInventory() {
        return "admin-inventory";
    }
}