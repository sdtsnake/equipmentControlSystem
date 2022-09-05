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
            "the password must contain: " +
            "1. start of the string" +
            "2. a digit must occur at least once" +
            "3. a lower case letter must occur at least once" +
            "4. an upper case letter must occur at least once" +
            "5. 4-8 character password, both inclusive" +
            "6.  end of the string"
    )
    private String password;
    @Schema(description = "name", example="Ever troll care monda")
    private String name;
    @Schema(description = "this a rol user have in the app 1=admin, 2=user", example="1")
    private String rol;
}
