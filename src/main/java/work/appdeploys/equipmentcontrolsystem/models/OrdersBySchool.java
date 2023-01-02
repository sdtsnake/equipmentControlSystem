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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "view_ordersbyschool")
@Immutable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrdersBySchool that = (OrdersBySchool) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
