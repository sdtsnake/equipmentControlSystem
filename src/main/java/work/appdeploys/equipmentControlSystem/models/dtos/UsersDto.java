package work.appdeploys.equipmentControlSystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "users")
public class UsersDto {
    @Schema(description = "Code of user", example="1")
    private Long id;
    @Schema(description = "Email", example="adm@controlsystem.com")
    private String email;
    @Schema(description = "\n" +
            "the password must contain letters and numbers at least one " +
            "letter in uppercase, at least one special character with a " +
            "minimum of 8 characters", example="adm$123Abc")
    private String password;
    @Schema(description = "name", example="Evey troll care monda")
    private String name;
    @Schema(description = "this a rol user have in the app 1=admin, 2=user", example="1")
    private String rol;
}
