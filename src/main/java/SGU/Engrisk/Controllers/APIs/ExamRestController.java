package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.Exam.CreateExamDTO;
import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.DTO.Exam.UpdateExamDTO;
import SGU.Engrisk.Services.ExamService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamRestController {
    @Autowired
    ExamService examService;

    @GetMapping
    public List<ResponseExamDTO> index() {
        return examService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseExamDTO get(@PathVariable Long id) throws NotFoundException {
        return examService.getResponse(id);
    }

    @PostMapping
    public ResponseExamDTO create(@RequestBody CreateExamDTO dto) throws Exception {
        return examService.create(dto);
    }

    @PutMapping
    public ResponseExamDTO update(@RequestBody UpdateExamDTO dto) throws Exception {
        return examService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        examService.delete(id);
    }
}
