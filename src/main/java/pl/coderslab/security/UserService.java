package pl.coderslab.security;

import pl.coderslab.user.User;

public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);
}
