package cs157a.ecommerce157a;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String email, Model model) {
        // Replace this placeholder with real authentication once users are persisted.
        model.addAttribute("loginMessage", "Account sign-in will be available soon.");
        model.addAttribute("submittedEmail", email);
        return "home";
    }
}
