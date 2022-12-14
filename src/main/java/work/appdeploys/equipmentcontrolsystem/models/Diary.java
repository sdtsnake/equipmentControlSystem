package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table
@Data
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "idschool", nullable = false)
    private School school;

    @Column
    private String weekday;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @Column(name = "ending_time")
    @Temporal(TemporalType.TIME)
    private LocalTime endingTime;

    @Column
    private String replacement;

}
