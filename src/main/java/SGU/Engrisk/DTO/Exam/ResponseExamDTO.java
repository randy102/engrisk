package SGU.Engrisk.DTO.Exam;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Models.Room;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseExamDTO extends UpdateExamDTO {
//    private String roomIds;
//    private String attendanceIds;
    public static ResponseExamDTO convert(Exam exam) {
        ResponseExamDTO res = (new ModelMapper()).map(exam, ResponseExamDTO.class);

//        List<Room> rooms = exam.getRooms();
//        List<Attendance> attendances = exam.getAttendances();
//        if (attendances == null)
//            res.setAttendanceIds("");
//        else
//            res.setAttendanceIds(exam.getAttendances().stream().map(attendance -> String.valueOf(attendance.getId())).collect(Collectors.joining(",")));
//
//        if (rooms == null)
//            res.setRoomIds("");
//        else
//            res.setRoomIds(exam.getRooms().stream().map(r -> String.valueOf(r.getId())).collect(Collectors.joining(",")));

        return res;
    }
}
