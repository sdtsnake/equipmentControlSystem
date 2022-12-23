package work.appdeploys.equipmentcontrolsystem.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdersExcelDto {

    private String model;

    private String serialNumber;

    private Long asset;

    private String issue;

    private Long incident;

    private String note;

    private String statusOrder;

}

























