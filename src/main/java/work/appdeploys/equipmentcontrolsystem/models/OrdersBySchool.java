package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_ordersbyschool")
@Immutable
@Data
public class OrdersBySchool {

    @Id
    @Column(name = "ordernro", nullable = false)
    private Long orderNro;

    @Column(name = "orderdate", nullable = false)
    private Long orderDate;

    @Column(name = "orderschool", nullable = false)
    private Long orderSchool;

    @Column(name = "orderschoolname", nullable = false)
    private String orderSchoolName;


}
