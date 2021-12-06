package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAttendanceDTO{
    private AttendanceID id;

    private ResponseExamDTO exam;

    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;

    public static ResponseAttendanceDTO convert(Attendance attendance){
        ResponseAttendanceDTO res = (new ModelMapper()).map(attendance, ResponseAttendanceDTO.class);
        res.setExam(ResponseExamDTO.convert(attendance.getExam()));
        return res;
    }
}
