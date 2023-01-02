package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "view_statusorderbyschool")
@Immutable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StatusOrderBySchool {
    @Id
    @Column(name = "idview")
    private Long id;

    @Column(name = "status_order")
    private String statusOrder;

    @Column(name = "idschool", nullable = false)
    private Long idSchool;

    @Column(name = "nameschool", nullable = false)
    private String nameSchool;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Column(name = "datecreate", nullable = false)
    private LocalDate dateCreate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatusOrderBySchool that = (StatusOrderBySchool) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
