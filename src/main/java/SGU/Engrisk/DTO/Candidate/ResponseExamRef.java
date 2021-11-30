package SGU.Engrisk.DTO.Candidate;

import SGU.Engrisk.Models.Exam;
import SGU.Engrisk.lib.enums.ExamType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ResponseExamRef {
    private Long id;

    @ApiModelProperty(example = "2016-11-20 11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date examDate;

    @Enumerated(EnumType.STRING)
    private ExamType type;

    private String name;
    private Long price;

    public static ResponseExamRef convert(Exam exam){
        ResponseExamRef res = (new ModelMapper()).map(exam, ResponseExamRef.class);
        return res;
    }
}
