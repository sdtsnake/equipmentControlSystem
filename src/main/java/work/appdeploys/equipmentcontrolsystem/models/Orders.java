package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private String serial_number;
    private Long asset;
    private String issue;
    private Long incident;
    private String note;
    private String status_order;
    //@ManyToOne(optional = false,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "idusercreate", nullable = false)
    private Users idusercreate;
    private LocalDate datecreate;
    //@ManyToOne(optional = true,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "idusermod")
    private Users idusermod;
    private Long order_number;
}
