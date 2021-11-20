package SGU.Engrisk.Controllers;

import SGU.Engrisk.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @GetMapping("/candidate")
    public String index(Model model) {
        model.addAttribute("candidates", candidateService.getAll());
        return "Candidate/index";
    }

}
