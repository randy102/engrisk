package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.lib.enums.SexType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCandidateDTO {
    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date citizenIdDate;

    private String citizenId;

    @ApiModelProperty(required = true)
    private String name;
    private String phone;
    private String email;
    private String birthPlace;
    private String citizenIdPlace;

    @ApiModelProperty(required = true)
    private SexType sex;
}
