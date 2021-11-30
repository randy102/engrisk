package SGU.Engrisk.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
public class AttendanceID implements Serializable {
    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "exam_id")
    private Long examId;

    public AttendanceID(Long candidateId, Long examId) {
        this.candidateId = candidateId;
        this.examId = examId;
    }

}
