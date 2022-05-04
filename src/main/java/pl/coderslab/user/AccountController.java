package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping("/registration")
    public String registration() {
        return "/account/registration";
    }

//    What redirect?
    @PostMapping("/registration")
    public String registrationForm() {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }
}
