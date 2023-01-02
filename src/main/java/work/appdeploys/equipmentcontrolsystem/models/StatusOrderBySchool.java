package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "view_statusorderbyschool")
@Immutable
@Data
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

}
