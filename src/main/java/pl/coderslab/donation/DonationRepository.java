package pl.coderslab.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) from Donation d")
    Integer donationsQuantity();


}
