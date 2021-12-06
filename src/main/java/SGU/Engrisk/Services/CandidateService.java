package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Candidate.ResponseCandidateDTO;
import SGU.Engrisk.DTO.Candidate.UpdateCandidateDTO;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Repositories.CandidateRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseCandidateDTO> getAll() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<ResponseCandidateDTO> resList = candidates.stream().map(candidate -> {
            ResponseCandidateDTO res = modelMapper.map(candidate, ResponseCandidateDTO.class);
//            res.setAttendanceIds(candidate);
            return res;
        }).collect(Collectors.toList());
        return resList;
    }

    public Candidate get(long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public Candidate get(String name) {
        return candidateRepository.findByName(name).orElse(null);
    }

    public ResponseCandidateDTO getResponse(long id) throws NotFoundException {
        Candidate candidate = get(id);
        if (candidate == null) {
            throw new NotFoundException("Not Existed");
        }

        return ResponseCandidateDTO.convert(candidate);
    }

    public ResponseCandidateDTO create(CreateCandidateDTO dto) throws Exception {
        if (candidateRepository.existsByName(dto.getName())) {
            throw new EntityExistsException(dto.getName() + " Existed");
        }

        Candidate candidate = modelMapper.map(dto, Candidate.class);

        candidate = candidateRepository.save(candidate);

        return ResponseCandidateDTO.convert(candidate);
    }

    public ResponseCandidateDTO update(UpdateCandidateDTO dto) throws Exception {
        if (!candidateRepository.existsById(dto.getId())) {
            throw new NotFoundException("Not Existed");
        }

        if (candidateRepository.existsByName(dto.getName()) && get(dto.getName()).getId() != dto.getId()) {
            throw new EntityExistsException(dto.getName() + " Existed");
        }

        Candidate candidate = modelMapper.map(dto, Candidate.class);

        candidate = candidateRepository.save(candidate);

        return ResponseCandidateDTO.convert(candidate);
    }

    public void delete(Long id) throws NotFoundException {
        if (!candidateRepository.existsById(id))
            throw new NotFoundException("Not existed id: " + id);
        candidateRepository.deleteById(id);
    }
}
