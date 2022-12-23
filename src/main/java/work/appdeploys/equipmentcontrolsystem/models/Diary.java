package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private int weekday;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "ending_time")
    private LocalTime endingTime;

    @Column
    private String replacement;

}
