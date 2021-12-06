package SGU.Engrisk.DTO.Exam;

//import SGU.Engrisk.DTO.Attendance.ResponseAttendanceRef;
import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.Models.Room;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseExamDTO extends UpdateExamDTO {
//    private List<ResponseAttendanceRef> attendances;

    public static ResponseExamDTO convert(Exam exam) {
        ResponseExamDTO res = (new ModelMapper()).map(exam, ResponseExamDTO.class);
//        res.setAttendances(exam.getAttendances().stream().map(e -> ResponseAttendanceRef.convert(e)).collect(Collectors.toList()));
        return res;
    }
}
