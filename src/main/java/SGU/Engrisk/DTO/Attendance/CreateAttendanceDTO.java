package SGU.Engrisk.DTO.Attendance;

import SGU.Engrisk.Models.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttendanceDTO {
    private Long examId;

    private Long candidateId;


    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;
}
