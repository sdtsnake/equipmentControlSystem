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
import java.time.LocalDate;

@Entity
@Table
@Data
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
    @Column(name = "idusercreate")
    private Users idUserCreate;

    @Column(name = "datecreate")
    private LocalDate dateCreate;

    @ManyToOne
    @JoinColumn(name = "idusermod")
    @Column(name = "idusermod")
    private Users idUserMod;

    @Column(name = "order_number")
    private Long orderNumber;

}
