package pl.coderslab.user;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.institution.InstitutionRepository;
import pl.coderslab.security.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, InstitutionRepository institutionRepository, UserService userService) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
        this.userService = userService;
    }


    //    ADMIN VIEWS

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/user_list")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAllNonAdmins());
        return "/admin/userList";
    }

    @GetMapping("/admin/admin_list")
    public String adminList(Model model) {
        model.addAttribute("admins", userRepository.findAllAdmins());
        return "/admin/adminList";
    }

    @GetMapping("/admin/institution_list")
    public String institutionList(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "/admin/institutionList";
    }


//                                ADMIN CRUD

    @GetMapping("/admin/create_admin")
    public String createAdmin(Model model) {
        model.addAttribute("user", new User());
        return "/admin/createAdmin";
    }

    @PostMapping("/admin/create_admin")
    public String createAdminForm(@Valid User user, BindingResult result, @RequestParam String password2) {
//        Check email already exists
        List<String> allEmails = userRepository.findAllEmails();
        for (String email : allEmails) {
            if (email.equals(user.getEmail())) {
                result.rejectValue("email", "error.sameEmail", "Użytkownik z takim e-mailem juz istnieje");
                return "/admin/createAdmin";
            }
        }
        //Check blank password
        if (user.getPassword().isBlank()) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/admin/createAdmin";
        }
//        Check passwords match
        if (!user.getPassword().equals(password2)) {
            result.rejectValue("password", "error.passwordMatch", "Hasła nie są takie same");
            return "/admin/createAdmin";
        }
//        Validation errors
        if (result.hasErrors()) {
            return "/admin/createAdmin";
        }
        userService.saveAsAdmin(user);
        return "redirect:/admin/admin_list";
    }

    @GetMapping("/admin/delete_admin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        userRepository.delete(userRepository.getById(id));
        return "redirect:/admin/admin_list";
    }

    @GetMapping("/admin/edit_admin/{id}")
    public String editAdmin(@PathVariable Long id, Model model) {
        User admin = userRepository.getById(id);
        FakeUser fakeUser = new FakeUser();
        fakeUser.setEmail(admin.getEmail());
        fakeUser.setName(admin.getName());
        fakeUser.setSurname(admin.getSurname());
        fakeUser.setId(admin.getId());
        model.addAttribute("fakeUser", fakeUser);
        return "/admin/editAdmin";
    }

    @PostMapping("/admin/edit_admin")
    public String editAdminForm(@Valid FakeUser fakeUser, BindingResult result, @RequestParam String password2) {

        //Check blank password
        if (fakeUser.getPassword().isBlank()) {
            result.rejectValue("password", "error.emptyPassword", "Hasło nie może być puste");
            return "/admin/editAdmin";
        }
//        Check passwords match
        if (!fakeUser.getPassword().equals(password2)) {
            result.rejectValue("password", "error.passwordMatch", "Hasła nie są takie same");
            return "/admin/editAdmin";
        }
//        Validation errors
        if (result.hasErrors()) {
            return "/admin/editAdmin";
        }
        User editedAdmin = userRepository.getById(fakeUser.getId());
        editedAdmin.setName(fakeUser.getName());
        editedAdmin.setSurname(fakeUser.getSurname());
        editedAdmin.setEmail(fakeUser.getEmail());
        editedAdmin.setPassword(fakeUser.getPassword());
        userService.saveAsAdmin(editedAdmin);
        return "redirect:/admin/admin_list";
    }


//                    ADMIN - USER CRUD

    @GetMapping("/admin/edit_user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User originalUser = userRepository.getById(id);

        FakeUser fakeUser = new FakeUser();
        fakeUser.setId(originalUser.getId());
        fakeUser.setName(originalUser.getName());
        fakeUser.setSurname(originalUser.getSurname());
        fakeUser.setEmail(originalUser.getEmail());
        model.addAttribute("fakeUser",fakeUser);

        return "/user/adminEditUser";
    }

    @PostMapping("/admin/edit_user")
    public String editUserForm(@Valid FakeUser fakeUser, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/adminEditUser";
        }

        User editedUser = userRepository.getById(fakeUser.getId());
        editedUser.setName(fakeUser.getName());
        editedUser.setSurname(fakeUser.getSurname());
        editedUser.setEmail(fakeUser.getEmail());
        userRepository.save(editedUser);
        return "redirect:/admin/user_list";
    }

    @GetMapping("/admin/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.delete(userRepository.getById(id));
        return "redirect:/admin/user_list";
    }
}
