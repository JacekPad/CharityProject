package pl.coderslab.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.security.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    public AccountController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/account/registration";
    }

    @PostMapping("/registration")
    public String registrationForm(@Valid User user, BindingResult result, Model model, @RequestParam String password2) {

//        Check email already exists
        List<String> allEmails = userRepository.findAllEmails();
        for (String email : allEmails) {
            if (email.equals(user.getEmail())) {
                result.rejectValue("email","error.sameEmail","Użytkownik z takim emailem juz istnieje");
                return "/account/registration";
            }
        }

        //Check blank password
        if (user.getPassword().isBlank()) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/account/registration";
        }

//        Check passwords match
        if (!user.getPassword().equals(password2)) {
            result.rejectValue("password", "error.passwordMatch", "Hasła nie są takie same");
            return "/account/registration";
        }

//        Validation errors
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

    @GetMapping("/admin/user_list")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/admin/userList";
    }

}


