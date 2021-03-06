package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Candidate.ResponseCandidateDTO;
import SGU.Engrisk.DTO.Candidate.UpdateCandidateDTO;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Repositories.CandidateRepository;
import SGU.Engrisk.Utils.FormatString;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseCandidateDTO> getAll() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<ResponseCandidateDTO> resList = candidates.stream().map(candidate -> modelMapper.map(candidate, ResponseCandidateDTO.class)).collect(Collectors.toList());
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
        //Check name
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        //Check BirthDate
        if (dto.getBirthDate() == null || dto.getBirthDate().after(new Date()))
            throw new IllegalArgumentException("Birth Date cannot be null and must be after current date");

        //Check Sex
        if (dto.getSex() == null)
            throw new IllegalArgumentException("Sex cannot be null");

        dto.setName(FormatString.TitleCase(dto.getName()));

        //Check existed
        if (dto.getCitizenId() != null && candidateRepository.existsByCitizenId(dto.getCitizenId()))
            throw new EntityExistsException(dto.getCitizenId() + " existed");


        System.out.println("debug 1 " + dto);
        Candidate candidate = modelMapper.map(dto, Candidate.class);

        candidate = candidateRepository.save(candidate);
        System.out.println("debug 2 " + candidate);
        return ResponseCandidateDTO.convert(candidate);
    }

    public ResponseCandidateDTO update(UpdateCandidateDTO dto) throws Exception {
        //Check name
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        //Check BirthDate
        if (dto.getBirthDate() == null || dto.getBirthDate().after(new Date()))
            throw new IllegalArgumentException("Birth Date cannot be null and must be before current date");

        //Check Sex
        if (dto.getSex() == null)
            throw new IllegalArgumentException("Sex cannot be null");

        dto.setName(FormatString.TitleCase(dto.getName()));

        Candidate candidate = candidateRepository.getById(dto.getId());
        if (candidate == null) {
            throw new NotFoundException("Not Existed");
        }

        //Check citizenId
        if (dto.getCitizenId() != null) {
            Long candidateId = getIdByCitizen(dto.getCitizenId());
            if (candidateId != null && candidateId != candidate.getId()) {
                throw new EntityExistsException(dto.getCitizenId() + " existed");
            }
        }

        candidate.setBirthDate(dto.getBirthDate());
        candidate.setCitizenIdDate(dto.getCitizenIdDate());
        candidate.setCitizenId(dto.getCitizenId());
        candidate.setName(dto.getName());
        candidate.setPhone(dto.getPhone());
        candidate.setEmail(dto.getEmail());
        candidate.setBirthPlace(dto.getBirthPlace());
        candidate.setCitizenIdPlace(dto.getCitizenIdPlace());
        candidate.setSex(dto.getSex());

        candidate = candidateRepository.save(candidate);
        return ResponseCandidateDTO.convert(candidate);
    }

    public void delete(Long id) throws NotFoundException {
        Candidate candidate = get(id);
        if (candidate == null)
            throw new NotFoundException("Not existed id: " + id);
        if (!candidate.getAttendances().isEmpty())
            throw new NotFoundException("Already register exam");
        candidateRepository.deleteById(id);
    }

    public boolean existsById(Long candidateId) {
        return candidateRepository.existsById(candidateId);
    }

    public Long getIdByCitizen(String citizenId) {
        Optional<Candidate> existed = candidateRepository.findByCitizenId(citizenId);
        return existed.map(Candidate::getId).orElse(null);
    }
}
