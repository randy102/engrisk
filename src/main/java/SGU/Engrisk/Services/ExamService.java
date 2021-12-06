package SGU.Engrisk.Services;

import SGU.Engrisk.DTO.Exam.CreateExamDTO;
import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.DTO.Exam.UpdateExamDTO;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Repositories.ExamRepository;
import SGU.Engrisk.Repositories.RoomRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ResponseExamDTO> getAll() {
        List<Exam> exams = examRepository.findAll();

        List<ResponseExamDTO> resList = exams.stream().map(exam -> ResponseExamDTO.convert(exam)).collect(Collectors.toList());

        return resList;
    }

    public Exam get(long id) {
        return examRepository.findById(id).orElse(null);
    }

    public ResponseExamDTO getResponse(long id) throws NotFoundException {
        Exam exam = get(id);
        if (exam == null) {
            throw new NotFoundException("Not Existed");
        }
        return ResponseExamDTO.convert(exam);
    }

    public Exam get(String name) {
        return examRepository.findByName(name).orElse(null);
    }

    public ResponseExamDTO create(CreateExamDTO dto) throws Exception {
        if (examRepository.existsByName(dto.getName())) {
            throw new EntityExistsException(dto.getName() + " Existed");
        }

        Exam exam = modelMapper.map(dto, Exam.class);

        exam = examRepository.save(exam);
        return ResponseExamDTO.convert(exam);
    }

    public ResponseExamDTO update(UpdateExamDTO dto) throws Exception {
        if (!examRepository.existsById(dto.getId())) {
            throw new NotFoundException("Not Existed");
        }

        if (examRepository.existsByName(dto.getName()) && get(dto.getName()).getId() != dto.getId()) {
            throw new EntityExistsException(dto.getName() + " Existed");
        }

        Exam exam = modelMapper.map(dto, Exam.class);

        exam = examRepository.save(exam);
        return ResponseExamDTO.convert(exam);
    }

    public void delete(Long id) throws NotFoundException {
        if (!examRepository.existsById(id))
            throw new NotFoundException("Not existed id: " + id);
        examRepository.deleteById(id);
    }
}
