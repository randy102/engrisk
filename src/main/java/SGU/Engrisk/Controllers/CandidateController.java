package SGU.Engrisk.Controllers;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.ResponseAttendanceDTO;
import SGU.Engrisk.DTO.Candidate.CreateCandidateDTO;
import SGU.Engrisk.DTO.Attendance.RegisterAttendanceDTO;
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


@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    ExamService examService;

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("candidates", candidateService.getAll());
        return "Candidate/index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("dto", new RegisterAttendanceDTO());
        return "Candidate/register";
    }

    @PostMapping("/register-citizen")
    public String registerCitizen(@ModelAttribute("dto") RegisterAttendanceDTO dto) {
        Long candidateId = candidateService.getIdByCitizen(dto.getCitizenId());
        if (candidateId != null) {
            return "redirect:/candidate/register-exam/" + candidateId;
        }
        return "redirect:/candidate/register-candidate/" + dto.getCitizenId();
    }

    @GetMapping("/register-candidate/{citizen}")
    public String registerCandidate(Model model, @PathVariable(value = "citizen") String citizenId) {
        CreateCandidateDTO dto = new CreateCandidateDTO();
        dto.setCitizenId(citizenId);
        model.addAttribute("dto", dto);
        return "Candidate/register-candidate";
    }


    @PostMapping("/register-candidate")
    public String create(Model model, @ModelAttribute("dto") CreateCandidateDTO dto) throws Exception {
        try {
            candidateService.create(dto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("dto", dto);
            return "Candidate/register-candidate";
        }
        return "redirect:/candidate";
    }

    @GetMapping("/register-exam/{candidateId}")
    public String registerCandidate(Model model, @PathVariable(value = "candidateId") Long candidateId) {
        RegisterAttendanceDTO dto = new RegisterAttendanceDTO();
        Candidate candidate = candidateService.get(candidateId);
        dto.setCitizenId(candidate.getCitizenId());
        dto.setCandidateId(candidateId);
        model.addAttribute("dto", dto);
        model.addAttribute("exams", examService.getRegistrable());
        return "Candidate/register-exam";
    }

    @PostMapping("/register-exam")
    public String create(Model model, @ModelAttribute("dto") RegisterAttendanceDTO dto) throws Exception {
        ResponseAttendanceDTO created;
        try {
            CreateAttendanceDTO createAttendanceDTO = modelMapper.map(dto, CreateAttendanceDTO.class);
            created = attendanceService.create(createAttendanceDTO);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("dto", dto);
            model.addAttribute("exams", examService.getRegistrable());
            return "Candidate/register-exam";
        }
        return "redirect:/candidate/attendance/" + created.getId().getCandidateId() + "/" + created.getId().getExamId();
    }

    @GetMapping("/attendance/{candidateId}/{examId}")
    public String viewAttendance(Model model, @PathVariable(value = "candidateId") Long candidateId, @PathVariable(value = "examId") Long examId) {
        Attendance attendance = attendanceService.get(new AttendanceID(candidateId, examId));
        System.out.println(attendance);
        ResponseAttendanceDTO dto = ResponseAttendanceDTO.convert(attendance);
        System.out.println(dto);
        model.addAttribute("dto", dto);
        return "Candidate/attendance";
    }

}
