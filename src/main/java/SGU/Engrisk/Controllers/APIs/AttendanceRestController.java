package SGU.Engrisk.Controllers.APIs;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.ResponseAttendanceDTO;
import SGU.Engrisk.DTO.Attendance.UpdateAttendanceDTO;
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

    @GetMapping
    public List<ResponseAttendanceDTO> index() {
        return attendanceService.getAll();
    }

//    @GetMapping
//    public List<ResponseAttendanceDTO> filter(@RequestParam Optional<Long> examId,
//                                              @RequestParam Optional<Long> roomId,
//                                              @RequestParam Optional<String> roomCode,
//                                              @RequestParam Optional<String> name) throws NotFoundException {
//        if (examId.isPresent())
//            return attendanceService.getResponseByExamId(examId.get());
//        if (roomId.isPresent())
//            return attendanceService.getResponseByRoomId(roomId.get());
//        if (roomCode.isPresent())
//            return attendanceService.getResponseByRoomCode(roomCode.get());
//        if (name.isPresent())
//            return attendanceService.getResponseByNameLike(name.get());
//        return attendanceService.getAll();
//    }

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
    public ResponseAttendanceDTO update(@RequestBody UpdateAttendanceDTO dto) throws Exception {
        return attendanceService.update(dto);
    }


    @DeleteMapping("/id")
    public ResponseAttendanceDTO delete(@RequestParam Long candidateId, @RequestParam Long examId) throws NotFoundException {
        return attendanceService.delete(new AttendanceID(candidateId, examId));
    }

}
