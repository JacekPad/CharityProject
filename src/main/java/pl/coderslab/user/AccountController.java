package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.emailSender.EmailService;
import pl.coderslab.security.VerificationToken;
import pl.coderslab.security.VerificationTokenRepository;
import pl.coderslab.security.UserServiceImpl;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class AccountController {
    private final UserServiceImpl userService;
    private final EmailService emailService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public AccountController(UserServiceImpl userService, EmailService emailService, VerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.userService = userService;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/account/registration";
    }

    @PostMapping("/registration")
    public String registrationForm(@Valid User user, BindingResult result, @RequestParam String password2, Model model) {
        String password = user.getPassword();
//        Check email already exists
        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.sameEmail", "Użytkownik z takim e-mailem juz istnieje");
            return "/account/registration";
        }
        //Check blank password
        if (userService.blankPassword(password)) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/account/registration";
        }
//        Check passwords match
        if (!userService.samePasswords(password, password2)) {
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
        String uuid = String.valueOf(UUID.randomUUID());
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setTokenUUID(uuid);
        tokenRepository.save(verificationToken);

        emailService.sendSimpleMessage(user.getEmail(), "Registration - Final step",
                "To activate your account click in this link: \n http://localhost:8080/registration/" + verificationToken.getTokenUUID());

        model.addAttribute("token",verificationToken);
        return "/account/activateAccount";

    }

    @GetMapping("/login")
    public String login() {
        return "/account/login";
    }

    @GetMapping("/registration/{tokenUUID}")
    public String verifyRegistration(@PathVariable String tokenUUID) {
        VerificationToken token = tokenRepository.findByTokenUUID(tokenUUID);

        if (token == null) {
            return "redirect:/registration_expired";
        }

        User verifiedUser = userRepository.getById(token.getUser().getId());
        if (verifiedUser.getEnabled() == 1) {
            return "redirect:/registration_completed";
        }
        verifiedUser.setEnabled(1);
        userRepository.save(verifiedUser);
        tokenRepository.delete(token);
        return "redirect:/registration_completed";
    }

    @GetMapping("/registration_completed")
    public String registrationCompleted() {
        return "/account/registrationCompleted";
    }

    @GetMapping("/registration_expired")
    public String registrationExpired() {
        return "/account/registrationExpired";
    }

    @PostMapping("/registration_new_activation")
    public String resendActivationLink(@RequestParam Long tokenId) {
        VerificationToken token = tokenRepository.getById(tokenId);
        String tokenUUID = token.getTokenUUID();
        String email = token.getUser().getEmail();
        emailService.sendSimpleMessage(email, "Registration - Final step",
                "To activate your account click in this link: \n http://localhost:8080/registration/" + tokenUUID);
        return "/account/newVerificationToken";
    }

}


