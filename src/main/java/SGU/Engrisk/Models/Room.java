package SGU.Engrisk.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exam exam;

    private String name;

    @Transient
    public static final int CAPACITY=35;

    public Room(Exam exam, String name) {
        this.exam=exam;
        this.name=name;
    }
}