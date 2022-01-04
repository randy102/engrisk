package SGU.Engrisk.DTO.Attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttendanceResultDTO extends CreateAttendanceDTO {
    private Float listening;
    private Float speaking;
    private Float reading;
    private Float writing;

    public UpdateAttendanceResultDTO(Long examId, Long candidateId, Float listening, Float speaking, Float reading, Float writing) {
        super(examId, candidateId);
        this.listening = listening;
        this.speaking = speaking;
        this.reading = reading;
        this.writing = writing;
    }
}
