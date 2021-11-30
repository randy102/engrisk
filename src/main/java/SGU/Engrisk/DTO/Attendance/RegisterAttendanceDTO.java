package SGU.Engrisk.DTO.Attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAttendanceDTO extends CreateAttendanceDTO {
    private String citizenId;
}
