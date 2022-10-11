package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "Order Response")
@EqualsAndHashCode(callSuper=false)
public class OrderResponseDto  extends  OrdersDto{

    @Schema(description = "Id user create", example = "2")
    private UserResponseDto idUserMod;

    @Schema(description = "Id user create", example = "2")
    private UserResponseDto idUserCreate;
}
