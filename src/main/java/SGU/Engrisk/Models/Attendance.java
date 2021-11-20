package SGU.Engrisk.Models;

import SGU.Engrisk.lib.enums.SexType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance implements Serializable {
    @EmbeddedId
    private AttendanceID id;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String code;
    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;

    public Attendance(Exam exam, Candidate candidate) {
        this.id = new AttendanceID(candidate.getId(), exam.getId());
        this.exam = exam;
        this.candidate = candidate;
    }
}