package SGU.Engrisk.DTO.Attendance;

import SGU.Engrisk.Models.AttendanceID;
import io.swagger.annotations.ApiModelProperty;
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
}
