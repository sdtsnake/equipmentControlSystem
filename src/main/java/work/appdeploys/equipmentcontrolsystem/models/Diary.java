package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Diary diary = (Diary) o;
        return id != null && Objects.equals(id, diary.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
