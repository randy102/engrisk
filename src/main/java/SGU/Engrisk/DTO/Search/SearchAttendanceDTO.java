package SGU.Engrisk.DTO.Search;

import SGU.Engrisk.DTO.Attendance.CreateAttendanceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAttendanceDTO {
    private String candidateName;
    private String candidatePhone;
}
