package SGU.Engrisk.DTO.Candidate;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCandidateDTO extends CreateCandidateDTO{
    @ApiModelProperty(required = true)
    private Long id;
}
