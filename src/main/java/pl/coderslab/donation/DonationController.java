package pl.coderslab.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.category.CategoryRepository;
import pl.coderslab.institution.InstitutionRepository;

import javax.validation.Valid;

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

        model.addAttribute("donation",new Donation());
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("institutions",institutionRepository.findAll());

        return "/donation/donationForm";
    }

    @PostMapping("/add")
    @ResponseBody   //    delete this later
    public String addDonationConfirmation(@Valid Donation donation, BindingResult result, Model model) {


        return "/donation/donationConfirmation";
    }

}
