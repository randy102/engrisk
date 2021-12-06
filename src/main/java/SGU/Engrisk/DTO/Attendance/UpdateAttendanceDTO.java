package SGU.Engrisk.DTO.Attendance;

import SGU.Engrisk.Models.AttendanceID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttendanceDTO extends CreateAttendanceDTO {
    private AttendanceID id;
}
