package SGU.Engrisk.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    private Room room;

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