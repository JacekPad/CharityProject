package pl.coderslab.security;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.user.User;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tokenUUID;
    @ManyToOne
    User user;
}
