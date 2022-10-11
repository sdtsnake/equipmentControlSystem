package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponseDto {
    @Schema(description = "Code of user", example="1")
    private Long id;
    @Schema(description = "Email", example="adm@controlsystem.com")
    private String email;
    @Schema(description = "name", example="Ever troll care monda")
    private String name;
    @Schema(description = "this a rol user have in the app 1=admin, 2=user", example="1")
    private String rol;
}
