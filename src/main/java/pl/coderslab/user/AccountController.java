package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.mailSender.EmailService;
import pl.coderslab.security.UserServiceImpl;

import javax.validation.Valid;

@Controller
public class AccountController {
    private final UserServiceImpl userService;
    private final EmailService emailService;

    public AccountController(UserServiceImpl userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/account/registration";
    }

    @PostMapping("/registration")
    public String registrationForm(@Valid User user, BindingResult result, @RequestParam String password2) {
        String password = user.getPassword();
//        Check email already exists
        if(userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.sameEmail", "Użytkownik z takim e-mailem juz istnieje");
            return "/account/registration";
        }
        //Check blank password
        if (userService.blankPassword(password)) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/account/registration";
        }
//        Check passwords match
        if (!userService.samePasswords(password,password2)) {
            result.rejectValue("password", "error.passwordMatch", "Hasła nie są takie same");
            return "/account/registration";
        }

//        Check password length
        if (!userService.passwordLength(password)) {
            result.rejectValue("password", "error.passwordLength", "Hasła musi mieć conajmniej 8 znaków");
            return "/account/registration";
        }

//        Check password regex
        if (!userService.passwordRegex(password)) {
            result.rejectValue("password", "error.passwordRegex", "Hasło musi zawierać małą oraz dużą literę, cyfrę oraz jeden ze znaków '*/.@_-'");
            return "/account/registration";
        }

//        Validation errors
        if (result.hasErrors()) {
            return "/account/registration";
        }
        userService.saveAsUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

}


