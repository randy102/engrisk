package SGU.Engrisk.DTO.Exam;

import SGU.Engrisk.lib.enums.ExamType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date exam_date;

    private ExamType type;

    private String name;
    private Long price;

}
