package pl.coderslab.security;

import pl.coderslab.user.User;

public interface UserService {

    User findByEmail(String email);

    void saveAsUser(User user);

    void saveAsAdmin(User user);

}
