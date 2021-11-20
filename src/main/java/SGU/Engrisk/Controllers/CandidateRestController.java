package SGU.Engrisk.Controllers;

import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Candidate.UpdateCandidateDTO;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Services.CandidateService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateRestController {
    @Autowired
    CandidateService candidateService;

    @GetMapping
    public List<Candidate> index() {
        return candidateService.getAll();
    }

    @PostMapping
    public Candidate create(@RequestBody CreateCandidateDTO dto){
        return candidateService.create(dto);
    }

    @PutMapping
    public Candidate update(@RequestBody UpdateCandidateDTO dto) throws NotFoundException {
        return candidateService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        candidateService.delete(id);
    }
}
