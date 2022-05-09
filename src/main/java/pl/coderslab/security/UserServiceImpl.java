package pl.coderslab.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.user.User;
import pl.coderslab.user.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveAsUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setEnabled(0);
        userRepository.save(user);
    }

    @Override
    public void saveAsAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        Role userAdmin = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole,userAdmin)));
        user.setEnabled(1);
        userRepository.save(user);
    }

    @Override
    public boolean blankPassword(String password) {
        return password.isBlank();
    }

    @Override
    public boolean samePasswords(String password1, String password2) {
        return password1.equals(password2);
    }

    @Override
    public boolean emailExists(String email) {
        List<String> allEmails = userRepository.findAllEmails();
        for (String emails : allEmails) {
            if (emails.equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean passwordLength(String password) {
        return password.length() >= 8;
    }

    @Override
    public boolean passwordRegex(String password) {
        Pattern patternCapital = Pattern.compile(".*[A-Z].*");
        Pattern patternSmall = Pattern.compile(".*[a-z].*");
        Pattern patternNumber = Pattern.compile(".*[0-9].*");
        Pattern patternSpecial = Pattern.compile(".*[_\\-@\\.\\*/].*");
        boolean matcherCapital = patternCapital.matcher(password).matches();
        boolean matcherSmall = patternSmall.matcher(password).matches();
        boolean matcherNumber = patternNumber.matcher(password).matches();
        boolean matcherSpecial = patternSpecial.matcher(password).matches();
        return (matcherCapital && matcherNumber && matcherSmall && matcherSpecial);
    }

    @Override
    public boolean emptyPassword(String password) {
        return password.isEmpty();
    }
}
