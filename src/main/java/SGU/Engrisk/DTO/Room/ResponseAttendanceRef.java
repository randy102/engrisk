package SGU.Engrisk.DTO.Room;

import SGU.Engrisk.DTO.Exam.ResponseCandidateRef;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAttendanceRef {
    private AttendanceID id;

    private ResponseCandidateRef candidate;

    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;
    private String code;

    public static ResponseAttendanceRef convert(Attendance attendance) {
        ResponseAttendanceRef res = (new ModelMapper()).map(attendance, ResponseAttendanceRef.class);
        res.setCandidate(ResponseCandidateRef.convert(attendance.getCandidate()));
        return res;
    }
}
