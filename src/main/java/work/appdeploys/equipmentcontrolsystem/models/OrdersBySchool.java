package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "view_ordersbyschool")
@Immutable
@Data
public class OrdersBySchool implements Serializable {

    @Id
    @Column(name = "idview")
    private Long id;

    @Column(name = "ordernro")
    private Long orderNro;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "orderschool", nullable = false)
    private Long orderSchool;

    @Column(name = "orderschoolname", nullable = false)
    private String orderSchoolName;
}
