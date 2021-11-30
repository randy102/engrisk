package SGU.Engrisk.DTO.Attendance;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttendanceDTO {
    @ApiModelProperty(required = true)
    private Long examId;

    @ApiModelProperty(required = true)
    private Long candidateId;
}
