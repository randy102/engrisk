package SGU.Engrisk.DTO.Exam;

import SGU.Engrisk.lib.enums.ExamType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamDTO {

    @ApiModelProperty(required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date exam_date;

    @ApiModelProperty(required = true)
    private ExamType type;

    @ApiModelProperty(required = true)
    private String name;

    private Long price;
}
