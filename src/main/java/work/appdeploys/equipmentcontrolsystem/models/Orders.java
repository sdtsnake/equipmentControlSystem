package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String serial;
    private Long asset;
    private String issue;
    private Long incident_id;
    private String note;
    private String status;
    @ManyToOne(optional = false,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users idusercreate;
    private Date datecreate;
    @ManyToOne(optional = true,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users idusermod;
    private Long order;
}
