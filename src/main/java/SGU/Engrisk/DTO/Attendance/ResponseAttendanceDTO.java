package SGU.Engrisk.DTO.Attendance;

import SGU.Engrisk.DTO.Candidate.ResponseExamRef;
import SGU.Engrisk.DTO.Exam.ResponseCandidateRef;
import SGU.Engrisk.DTO.Exam.ResponseRoomRef;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAttendanceDTO {

    private AttendanceID id;

    private ResponseExamRef exam;
    private ResponseCandidateRef candidate;
    private ResponseRoomRef room;

    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;
    private String code;

    public static ResponseAttendanceDTO convert(Attendance attendance) {
        ResponseAttendanceDTO res = (new ModelMapper()).map(attendance, ResponseAttendanceDTO.class);
        res.setCandidate(ResponseCandidateRef.convert(attendance.getCandidate()));
        res.setExam(ResponseExamRef.convert(attendance.getExam()));
        if (attendance.getRoom() != null)
            res.setRoom(ResponseRoomRef.convert(attendance.getRoom()));
        if (attendance.getRoom() != null)
            res.setRoom(ResponseRoomRef.convert(attendance.getRoom()));
        return res;
    }
}
