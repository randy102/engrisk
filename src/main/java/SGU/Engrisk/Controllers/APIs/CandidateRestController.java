package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Candidate.ResponseCandidateDTO;
import SGU.Engrisk.DTO.Candidate.UpdateCandidateDTO;
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
    public List<ResponseCandidateDTO> index() {
        return candidateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseCandidateDTO get(@PathVariable Long id) throws NotFoundException {
        return candidateService.getResponse(id);
    }

    @PostMapping
    public ResponseCandidateDTO create(@RequestBody CreateCandidateDTO dto) throws Exception {
        return candidateService.create(dto);
    }

    @PutMapping
    public ResponseCandidateDTO update(@RequestBody UpdateCandidateDTO dto) throws Exception {
        return candidateService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        candidateService.delete(id);
    }
}