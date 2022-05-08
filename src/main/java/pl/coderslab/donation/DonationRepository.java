package pl.coderslab.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.user.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) from Donation d")
    Integer donationsQuantity();

    @Query("select d from Donation d where d.user=?1 order by d.pickedUp desc, d.pickUpDate, d.created")
    List<Donation> findAllByUserCustomOrder(User user);
}
