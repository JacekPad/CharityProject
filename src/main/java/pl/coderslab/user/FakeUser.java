package pl.coderslab.user;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class FakeUser {
    private Long id;
    @Email
    @NotEmpty
    private String email;
    private String password;
    private String name;
    private String surname;
}
