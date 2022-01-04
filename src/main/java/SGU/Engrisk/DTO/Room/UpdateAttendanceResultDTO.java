package SGU.Engrisk.DTO.Room;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttendanceResultDTO {
    @ApiModelProperty(required = true)
    private Long candidateId;

    @ApiModelProperty(required = true)
    private Float listening;

    @ApiModelProperty(required = true)
    private Float speaking;

    @ApiModelProperty(required = true)
    private Float reading;

    @ApiModelProperty(required = true)
    private Float writing;
}
