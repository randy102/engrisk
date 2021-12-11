package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAttendanceRef {
    private AttendanceID id;

    private ResponseExamRef exam;

    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;
    private String code;

    public static ResponseAttendanceRef convert(Attendance attendance) {
        ResponseAttendanceRef res = (new ModelMapper()).map(attendance, ResponseAttendanceRef.class);
        res.setExam(ResponseExamRef.convert(attendance.getExam()));
        return res;
    }
}
