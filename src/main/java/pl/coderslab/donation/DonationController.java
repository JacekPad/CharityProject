package pl.coderslab.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.category.CategoryRepository;
import pl.coderslab.institution.InstitutionRepository;
import pl.coderslab.security.CurrentUser;
import pl.coderslab.user.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository,
                              InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/add")
    public String addDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "/donation/donationForm";
    }

    @PostMapping("/add")
    public String addDonationConfirmation(@Valid Donation donation, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {

        if(result.hasErrors()) {
            return "/donation/donationForm";
        }
        donation.setUser(currentUser.getUser());
        donationRepository.save(donation);
        return "redirect:/donation/confirmation";
    }

    @GetMapping("/confirmation")
    public String addDonationConfirmationPage() {
        return "/donation/donationFormConfirmation";
    }

    @GetMapping("/show_list")
    public String showUsersDonations(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("donations", donationRepository.findAllByUserCustomOrder(user));
        return "/donation/showList";
    }
    @GetMapping("/donation_details/{id}")
    public String donationDetails(@PathVariable Long id, Model model) {
        model.addAttribute("donation",donationRepository.getById(id));
        return "/donation/donationDetails";
    }

    @GetMapping("/archive/{id}")
    public String archiveDonation(@PathVariable Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Donation donation = donationRepository.getById(id);
        donation.setPickedUp(true);
        donation.setPickUpDate(LocalDate.now());
        donation.setPickUpTime(LocalTime.parse(LocalTime.now().format(formatter)));
        donationRepository.save(donation);
        return "redirect:/donation/show_list#donations";
    }
}
