package SGU.Engrisk.Controllers;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.RegisterAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.ResponseAttendanceDTO;
import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Search.SearchAttendanceDTO;
import SGU.Engrisk.DTO.Search.SearchCertificateDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Services.AttendanceService;
import SGU.Engrisk.Services.CandidateService;
import SGU.Engrisk.Services.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    ExamService examService;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/search")
    public String search(Model model, @RequestParam Optional<String> candidateName, @RequestParam Optional<String> candidatePhone) {
        if (candidateName.isPresent() && candidatePhone.isPresent()) {
            model.addAttribute("dto", attendanceService.getByCandidateNameAndPhone(candidateName.get(), candidatePhone.get()));
        }
        model.addAttribute("search", new SearchAttendanceDTO());
        return "Attendance/index";
    }

    @GetMapping("/certificate")
    public String certificate(Model model, @RequestParam Optional<String> code, @RequestParam Optional<String> exam) {

        if (code.isPresent() && exam.isPresent()) {
            model.addAttribute("dto", attendanceService.getByCodeAndExam(code.get(), exam.get()));
        } else {
            model.addAttribute("dto", null);
        }
        model.addAttribute("search", new SearchCertificateDTO());
        model.addAttribute("exams", examService.getAll());
        return "Attendance/certificate";
    }
}
