package cs157a.ecommerce157a;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/create-account")
    public String createAccount() {
        return "create-account";
    }

    @PostMapping("/create-account")
    public String submitAccount(@RequestParam String email, Model model) {
        // Replace this placeholder with account persistence and validation.
        model.addAttribute("accountMessage", "Account creation will be available soon.");
        model.addAttribute("submittedEmail", email);
        return "create-account";
    }
}
