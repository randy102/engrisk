package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.ResponseAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.UpdateAttendanceResultDTO;
import SGU.Engrisk.Models.AttendanceID;
import SGU.Engrisk.Services.AttendanceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRestController {
    @Autowired
    AttendanceService attendanceService;


//    @GetMapping
//    public List<ResponseAttendanceDTO> filter(@RequestParam (required = false) Optional<Long> examId,
//                                              @RequestParam (required = false) Optional<String> roomCode,
//                                              @RequestParam (required = false) Optional<String> name) throws NotFoundException {
//        if (examId.isPresent() && roomCode.isPresent())
//            return attendanceService.getResponseByExamIdAndRoomName(examId.get(), roomCode.get());
//        if (examId.isPresent())
//            return attendanceService.getResponseByExamId(examId.get());
//        if (name.isPresent())
//            return attendanceService.getResponseByNameLike(name.get());
//        return attendanceService.getAll();
//    }

    @GetMapping
    public List<ResponseAttendanceDTO> getAll() {
        return attendanceService.getAll();
    }

    @GetMapping("/id")
    public ResponseAttendanceDTO get(@RequestParam Long candidateId, @RequestParam Long examId) throws NotFoundException {
        return attendanceService.getResponse(new AttendanceID(candidateId, examId));
    }

    @GetMapping("/result")
    public ResponseAttendanceDTO getResult(@RequestParam String code) throws NotFoundException {
        return attendanceService.getResponseByCode(code);
    }


    @PostMapping
    public ResponseAttendanceDTO create(@RequestBody CreateAttendanceDTO dto) throws Exception {
        return attendanceService.create(dto);
    }

    @PutMapping
    public ResponseAttendanceDTO updateResult(@RequestBody UpdateAttendanceResultDTO dto) throws Exception {
        return attendanceService.updateResult(dto);
    }


    @DeleteMapping("/id")
    public ResponseAttendanceDTO delete(@RequestParam Long candidateId, @RequestParam Long examId) throws NotFoundException {
        return attendanceService.delete(new AttendanceID(candidateId, examId));
    }

}
