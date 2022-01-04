package SGU.Engrisk.DTO.Room;

import SGU.Engrisk.DTO.Candidate.ResponseExamRef;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Room;
import SGU.Engrisk.Services.AttendanceService;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseRoomDTO {
    private Long id;
    private String name;
    private ResponseExamRef exam;
    private List<ResponseAttendanceRef> attendances;
    public static ResponseRoomDTO convert(Room room) {
        ResponseRoomDTO res = (new ModelMapper()).map(room, ResponseRoomDTO.class);
        res.setExam(ResponseExamRef.convert(room.getExam()));
        if (room.getExam().getAttendances() != null)
            res.setAttendances(room.getExam().getAttendances().stream().map(a -> ResponseAttendanceRef.convert(a)).collect(Collectors.toList()));
        return res;
    }
}
