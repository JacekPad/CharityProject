package pl.coderslab.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u.email from User u")
    List<String> findAllEmails();

    @Query("SELECT distinct u from User u join u.roles r where r.id=2")
    List<User> findAllAdmins();

    @Query("select u from User u join u.roles r group by u.id having sum(r.id)=1")
    List<User> findAllNonAdmins();
}
