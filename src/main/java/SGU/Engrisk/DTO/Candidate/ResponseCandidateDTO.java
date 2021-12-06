package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.DTO.Exam.ResponseExamDTO;
import SGU.Engrisk.Models.Attendance;
import SGU.Engrisk.Models.Candidate;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseCandidateDTO extends UpdateCandidateDTO {
    private List<ResponseAttendanceDTO> attendances;
    public static ResponseCandidateDTO convert(Candidate candidate) {
        ResponseCandidateDTO res = (new ModelMapper()).map(candidate, ResponseCandidateDTO.class);
        res.setAttendances(candidate.getAttendances().stream().map(c -> ResponseAttendanceDTO.convert(c)).collect(Collectors.toList()));
        return res;
    }
}
