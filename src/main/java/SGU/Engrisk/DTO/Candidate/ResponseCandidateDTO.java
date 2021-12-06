package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Candidate;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseCandidateDTO extends UpdateCandidateDTO {
//    private String attendanceIds;
//
//    public void setAttendanceIds(Candidate candidate) {
//        List<Attendance> attendances = candidate.getAttendances();
//        if (attendances == null) {
//            this.attendanceIds = "";
//            return;
//        }
//        this.attendanceIds = candidate.getAttendances().stream().map(attendance -> String.valueOf(attendance.getId())).collect(Collectors.joining(","));
//    }

    public static ResponseCandidateDTO convert(Candidate candidate) {
        ResponseCandidateDTO res = (new ModelMapper()).map(candidate, ResponseCandidateDTO.class);

//        res.setAttendanceIds(candidate);
        return res;
    }
}
