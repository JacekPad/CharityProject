package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.security.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class AccountController {
    private final UserServiceImpl userService;

    public AccountController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/account/registration";
    }

    //    What redirect?
    @PostMapping("/registration")
    public String registrationForm(@Valid User user, BindingResult result, Model model, @RequestParam String password2) {
        if (user.getPassword().isBlank()) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/account/registration";
        }
        if (!user.getPassword().equals(password2)) {
            result.rejectValue("password", "error.passwordMatch", "Hasła nie są takie same");
            return "/account/registration";
        }
        if (result.hasErrors()) {
            return "/account/registration";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        User user = userService.findByEmail("1@g.com");
        return user.toString();
    }
}


