package SGU.Engrisk.DTO;

import SGU.Engrisk.lib.enums.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private ExamType type;
    private Long numExam;
    private Long numRoom;
    private Long numCandidate;
}
