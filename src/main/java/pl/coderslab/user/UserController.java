package pl.coderslab.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.institution.InstitutionRepository;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.security.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepository, InstitutionRepository institutionRepository, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
    public String adminList(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("currentUser",currentUser.getUser());
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
        String password = user.getPassword();
//        Check email already exists
        if(userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.sameEmail", "U??ytkownik z takim e-mailem juz istnieje");
            return "/admin/createAdmin";
        }
        //Check blank password
        if (userService.blankPassword(password)) {
            result.rejectValue("password", "error.emptyPassword", "Has??o nie mo??e by?? puste");
            return "/admin/createAdmin";
        }
//        Check passwords match
        if (!userService.samePasswords(password,password2)) {
            result.rejectValue("password", "error.passwordMatch", "Has??a nie s?? takie same");
            return "/admin/createAdmin";
        }

        //        Check password length
        if (!userService.passwordLength(password)) {
            result.rejectValue("password", "error.passwordLength", "Has??a musi mie?? conajmniej 8 znak??w");
            return "/admin/createAdmin";
        }

//        Check password regex
        if (!userService.passwordRegex(password)) {
            result.rejectValue("password", "error.passwordRegex", "Has??o musi zawiera?? ma???? oraz du???? liter??, cyfr?? oraz jeden ze znak??w '*/.@_-'");
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
        User user = new User();
        user.setEmail(admin.getEmail());
        user.setName(admin.getName());
        user.setSurname(admin.getSurname());
        user.setId(admin.getId());
        model.addAttribute("user", user);
        return "/admin/editAdmin";
    }

    @PostMapping("/admin/edit_admin")
    public String editAdminForm(@Valid User user, BindingResult result, @RequestParam String password2) {
        String password = user.getPassword();
        //Check blank password
        if (userService.blankPassword(password)) {
            result.rejectValue("password", "error.emptyPassword", "Has??o nie mo??e by?? puste");
            return "/admin/editAdmin";
        }
//        Check passwords match
        if (!userService.samePasswords(password,password2)) {
            result.rejectValue("password", "error.passwordMatch", "Has??a nie s?? takie same");
            return "/admin/editAdmin";
        }

        //        Check password length
        if (!userService.passwordLength(password)) {
            result.rejectValue("password", "error.passwordLength", "Has??a musi mie?? conajmniej 8 znak??w");
            return "/admin/editAdmin";
        }

//        Check password regex
        if (!userService.passwordRegex(password)) {
            result.rejectValue("password", "error.passwordRegex", "Has??o musi zawiera?? ma???? oraz du???? liter??, cyfr?? oraz jeden ze znak??w '*/.@_-'");
            return "/admin/editAdmin";
        }
//        Validation errors
        if (result.hasErrors()) {
            return "/admin/editAdmin";
        }
        User editedAdmin = userRepository.getById(user.getId());
        editedAdmin.setName(user.getName());
        editedAdmin.setSurname(user.getSurname());
        editedAdmin.setEmail(user.getEmail());
        editedAdmin.setPassword(user.getPassword());
        userService.saveAsAdmin(editedAdmin);
        return "redirect:/admin/admin_list";
    }


//                    ADMIN - USER CRUD

    @GetMapping("/admin/edit_user/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User originalUser = userRepository.getById(id);

        User user = new User();
        user.setId(originalUser.getId());
        user.setName(originalUser.getName());
        user.setSurname(originalUser.getSurname());
        user.setEmail(originalUser.getEmail());
        model.addAttribute("user", user);

        return "/user/adminEditUser";
    }

    @PostMapping("/admin/edit_user")
    public String editUserForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/adminEditUser";
        }

        User editedUser = userRepository.getById(user.getId());
        editedUser.setName(user.getName());
        editedUser.setSurname(user.getSurname());
        editedUser.setEmail(user.getEmail());
        userRepository.save(editedUser);
        return "redirect:/admin/user_list";
    }

    @GetMapping("/admin/delete_user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.delete(userRepository.getById(id));
        return "redirect:/admin/user_list";
    }

    @GetMapping("/admin/block_user/{id}")
    public String blockUser(@PathVariable Long id) {
        User userToBlock = userRepository.getById(id);
        userToBlock.setEnabled(0);
        userRepository.save(userToBlock);
        return "redirect:/admin/user_list";
    }

    @GetMapping("/admin/unblock_user/{id}")
    public String unblockUser(@PathVariable Long id) {
        User userToUnblock = userRepository.getById(id);
        userToUnblock.setEnabled(1);
        userRepository.save(userToUnblock);
        return "redirect:/admin/user_list";
    }


    //    USER CRUD

    @GetMapping("/user/profile")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = new User();
        User loggedUser = currentUser.getUser();

        user.setName(loggedUser.getName());
        user.setSurname(loggedUser.getSurname());
        user.setEmail(loggedUser.getEmail());
        user.setId(loggedUser.getId());
        model.addAttribute("user", user);
        return "/user/userProfile";
    }

    @PostMapping("/user/profile")
    public String userProfileEdit(@Valid User user, BindingResult result, @RequestParam String password2, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User loggedUser = currentUser.getUser();
        String password = user.getPassword();
        if (result.hasErrors()) {
            return "/user/userProfile";
        }

        //Check blank password
        if (userService.blankPassword(password) && !userService.emptyPassword(user.getPassword())) {
            result.rejectValue("password", "error.emptyPassword", "Has??o nie mo??e by?? puste");
            return "/user/userProfile";
        }
//        Check passwords match
        if (!userService.samePasswords(password,password2)) {
            result.rejectValue("password", "error.passwordMatch", "Has??a nie s?? takie same");
            return "/user/userProfile";
        }

        //        Check password length
        if (!userService.passwordLength(password)) {
            result.rejectValue("password", "error.passwordLength", "Has??a musi mie?? conajmniej 8 znak??w");
            return "/user/userProfile";
        }

//        Check password regex
        if (!userService.passwordRegex(password)) {
            result.rejectValue("password", "error.passwordRegex", "Has??o musi zawiera?? ma???? oraz du???? liter??, cyfr?? oraz jeden ze znak??w '*/.@_-'");
            return "/user/userProfile";
        }

        loggedUser.setEmail(user.getEmail());
        loggedUser.setName(user.getName());
        loggedUser.setSurname(user.getSurname());

//        Empty password -> no change in password
        if (user.getPassword().isEmpty()) {
            userRepository.save(loggedUser);
        } else {
            loggedUser.setPassword(passwordEncoder.encode(password));
            userRepository.save(loggedUser);
        }
        return "redirect:/user/profile";
    }

}
