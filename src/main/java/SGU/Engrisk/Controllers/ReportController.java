package SGU.Engrisk.Controllers;

import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.DTO.Exam.ResponseRoomRef;
import SGU.Engrisk.DTO.ReportDTO;
import SGU.Engrisk.DTO.Search.SearchAttendanceDTO;
import SGU.Engrisk.DTO.Search.SearchAttendanceRoomDTO;
import SGU.Engrisk.DTO.Search.SearchCertificateDTO;
import SGU.Engrisk.Services.AttendanceService;
import SGU.Engrisk.Services.CandidateService;
import SGU.Engrisk.Services.ExamService;
import SGU.Engrisk.Services.RoomService;
import SGU.Engrisk.lib.enums.ExamType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    ExamService examService;

    @GetMapping
    public String report(Model model) {
        List<ResponseExamDTO> exams = examService.getAll();
        List<ReportDTO> reportDTOList = new ArrayList<>();

        for (ExamType type : ExamType.values()) {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setType(type);

            List<ResponseExamDTO> examList = exams.stream().filter(exam -> exam.getType().equals(type)).collect(Collectors.toList());
            reportDTO.setNumExam((long) examList.size());
            reportDTO.setNumRoom(examList.stream().map(exam -> (long) exam.getRooms().size()).reduce(0L, Long::sum));
            reportDTO.setNumCandidate(examList.stream().map(exam -> (long) exam.getAttendances().size()).reduce(0L, Long::sum));
            reportDTOList.add(reportDTO);
        }

        model.addAttribute("dto", reportDTOList);
        return "Report/index";
    }
}
