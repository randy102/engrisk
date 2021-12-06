package SGU.Engrisk.DTO.Attendance;

import SGU.Engrisk.DTO.Candidate.ResponseCandidateDTO;
import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.AttendanceID;
import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.Models.Exam;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ResponseAttendanceDTO /*extends UpdateAttendanceDTO */{
//    private String code;
//    private Long roomId;

    private AttendanceID id;

    private ResponseExamDTO exam;
    private ResponseCandidateDTO candidate;

    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;

    public static ResponseAttendanceDTO convert(Attendance attendance){
        ResponseAttendanceDTO res = (new ModelMapper()).map(attendance, ResponseAttendanceDTO.class);
        res.setExam(ResponseExamDTO.convert(attendance.getExam()));
        res.setCandidate(ResponseCandidateDTO.convert(attendance.getCandidate()));
//        res.setExamId(attendance.getExam().getId());
//        res.setCandidateId(attendance.getCandidate().getId());
//        res.setRoomId(attendance.getRoom().getId());
        return res;
    }
}
