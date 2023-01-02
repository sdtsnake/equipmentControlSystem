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
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    private Long asset;

    private String issue;

    private Long incident;

    private String note;

    @Column(name = "status_order")
    private String statusOrder;

    @ManyToOne
    @JoinColumn(name = "idusercreate", nullable = false)
    private Users idUserCreate;

    @Column(name = "datecreate")
    private LocalDate dateCreate;

    @ManyToOne
    @JoinColumn(name = "idusermod")
    private Users idUserMod;

    @Column(name = "order_number")
    private Long orderNumber;

    @ManyToOne
    @JoinColumn(name = "idschool", nullable = false)
    private School school;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Orders orders = (Orders) o;
        return id != null && Objects.equals(id, orders.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
