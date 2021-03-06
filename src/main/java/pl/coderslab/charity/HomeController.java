package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.donation.DonationRepository;
import pl.coderslab.institution.InstitutionRepository;
import pl.coderslab.security.CurrentUser;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    public final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donations", donationRepository.count());
        model.addAttribute("donationsQuantity",donationRepository.donationsQuantity());
        return "index";
    }
}
