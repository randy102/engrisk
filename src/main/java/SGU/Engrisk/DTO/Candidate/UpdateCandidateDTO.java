package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.lib.enums.SexType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCandidateDTO extends CreateCandidateDTO{
    private Long id;
}
