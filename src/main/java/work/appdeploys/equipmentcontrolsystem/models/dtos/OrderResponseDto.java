package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderResponseDto {
    @Schema(description = "Code order", example="1")
    private Long id;
    @Schema(description = "model computer", example = "DELL 3100 CB")
    private String model;
    @Schema(description = "serial computer", example = "6c0v073")
    private String serialNumber;
    @Schema(description = "number asset", example = "71223117")
    private Long asset;
    @Schema(description = "issue", example="KEYBOARD")
    private String issue;
    @Schema(description = "number incident", example="INC0199012")
    private Long incident;
    @Schema(description = "note", example = "some one")
    private String note;
    @Schema(description = "status", example = "Fixed")
    private String statusOrder;
    @Schema(description = "Id user create", example = "2")
    private UserResponseDto idUserCreate;
    @Schema(description = "date create", example = "2021-01-01")
    private LocalDate dateCreate;
    @Schema(description = "Id user create", example = "2")
    private UserResponseDto idUserMod;
    @Schema(description = "csq order", example = "1")
    private Long orderNumber;

}
