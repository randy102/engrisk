package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Candidate.UpdateCandidateDTO;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Repositories.CandidateRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate get(long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public Candidate create(CreateCandidateDTO dto) throws EntityExistsException {
        return candidateRepository.save(modelMapper.map(dto, Candidate.class));
    }

    public Candidate update(UpdateCandidateDTO dto) throws NotFoundException {
        if (!candidateRepository.existsById(dto.getId())) {
            throw new NotFoundException("Not Existed");
        }
        return candidateRepository.save(modelMapper.map(dto, Candidate.class));
    }

    public void delete(Long id) throws NotFoundException {
        if (!candidateRepository.existsById(id))
            throw new NotFoundException("Not existed id: " + id);
        candidateRepository.deleteById(id);
    }
}
