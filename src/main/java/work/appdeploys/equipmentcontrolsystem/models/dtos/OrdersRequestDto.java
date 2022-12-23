package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Schema(description = "Orders Request")
public class OrdersRequestDto extends OrdersDto {

    @Schema(description = "Id user create", example = "2")
    private UsersDto idUserMod;

    @Schema(description = "Id user create", example = "2")
    private UsersDto idUserCreate;

    @Schema(description = "Id School", example = "1")
    private Long idschool;

}
