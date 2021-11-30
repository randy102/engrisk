package SGU.Engrisk.Models;

import SGU.Engrisk.lib.enums.ExamType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Room> rooms;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL/*, orphanRemoval = true*/)
    @ToString.Exclude
    private List<Attendance> attendances;

    @ApiModelProperty(example = "2016-11-20 11:11")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date examDate;

    @Enumerated(EnumType.STRING)
    private ExamType type;

    private String name;
    private Long price;

    public boolean isClose(){
        long differMiliSecond = this.examDate.getTime() - (new Date()).getTime();
        return TimeUnit.MILLISECONDS.toDays(differMiliSecond)<5;
    }
}