package SGU.Engrisk.DTO.Exam;

import SGU.Engrisk.Models.Candidate;
import SGU.Engrisk.lib.enums.SexType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ResponseCandidateRef {
    private Long id;

    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date citizenIdDate;

    @Enumerated(EnumType.STRING)
    private SexType sex;

    private String name;
    private String phone;
    private String email;
    private String birthPlace;
    private String citizenId;
    private String citizenIdPlace;

    public static ResponseCandidateRef convert(Candidate candidate){
        ResponseCandidateRef res = (new ModelMapper()).map(candidate, ResponseCandidateRef.class);
        return res;
    }
}
