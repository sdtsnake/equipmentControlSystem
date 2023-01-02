package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Schema(description = "Generate PDF to print by Date/School")
@EqualsAndHashCode(callSuper=false)
public class StatusOrderBySchoolResponseDto {
    @Schema(description = "id", example = "1")
    private Long id;

    @Schema(description = "Status order", example = "fixed")
    private String statusOrder;

    @Schema(description = "Id school", example = "1")
    private Long idSchool;

    @Schema(description = "Name School", example = "Lancaster School superior")
    private String nameSchool;

    @Schema(description = "Quantity Pc's", example = "5")
    private String quantity;

    @Schema(description = "Date Create", example = "2021-01-01")
    private LocalDate dateCreate;
}
