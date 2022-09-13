package work.appdeploys.equipmentcontrolsystem.models.dtos;

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
            "the password must contain: \n" +
            "1. The password must be between 8 and 16 characters long \n" +
            "2. with at least one digit \n" +
            "3. at least one lowercase letter \n" +
            "4. at least one uppercase letter.\n" +
            "5. It can NOT have other symbols ",
            example="w3Unpocodet0d0"
    )
    private String passwd;
    @Schema(description = "name", example="Ever troll care monda")
    private String name;
    @Schema(description = "this a rol user have in the app 1=admin, 2=user", example="1")
    private String rol;
}
