package SGU.Engrisk.DTO.Exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExamDTO extends CreateExamDTO{
    @ApiModelProperty(required = true)
    private Long id;
}
