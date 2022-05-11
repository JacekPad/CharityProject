package pl.coderslab.institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Getter
@ToString
public class Institution {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String name;
    @NotEmpty
    String description;

}
