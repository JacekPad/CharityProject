package pl.coderslab.donation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.category.Category;
import pl.coderslab.institution.Institution;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Donation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    Integer quantity;

    @ManyToMany
    List<Category> categories;

    @ManyToOne
    Institution institution;

    String street;
    String city;
    String zipCode;
    LocalDate pickUpDate;
    LocalTime pickUpTime;
    String pickUpComment;
    String phoneNumber;
}
