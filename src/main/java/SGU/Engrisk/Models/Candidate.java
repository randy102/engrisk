package SGU.Engrisk.Models;

import SGU.Engrisk.lib.enums.SexType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private List<Attendance> attendances;

    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @ApiModelProperty(example = "2016-11-20")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date citizenIdDate;

    private String name;
    private String phone;
    private String email;
    private String birthPlace;
    private String citizenId;
    private String citizenIdPlace;

    @Enumerated(EnumType.STRING)
    private SexType sex;
}