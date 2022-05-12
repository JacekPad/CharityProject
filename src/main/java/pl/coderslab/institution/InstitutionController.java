package pl.coderslab.institution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/admin/edit_institution/{id}")
    public String editInstitution(@PathVariable Long id, Model model) {
        model.addAttribute("institution", institutionRepository.getById(id));
        return "/institution/editInstitution";
    }

    @PostMapping("/admin/edit_institution")
    public String editInstitutionForm(@Valid Institution institution, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/institution/editInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution_list";
    }

    @GetMapping("/admin/delete_institution/{id}")
    public String deleteInstitution(@PathVariable Long id) {
        institutionRepository.delete(institutionRepository.getById(id));
        return "redirect:/admin/institution_list";
    }

    @GetMapping("/admin/add_institution")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "/institution/addInstitution";
    }

    @PostMapping("/admin/add_institution")
    public String addInstitutionForm(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "/institution/addInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution_list";
    }


}
