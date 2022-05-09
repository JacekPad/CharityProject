package pl.coderslab.security;

import pl.coderslab.user.User;

public interface UserService {

    User findByEmail(String email);

    void saveAsUser(User user);

    void saveAsAdmin(User user);

    boolean blankPassword(String password);

    boolean samePasswords(String password1, String password2);

    boolean emailExists(String email);

    boolean passwordLength(String password);

    boolean passwordRegex(String password);

    boolean emptyPassword(String password);

}
