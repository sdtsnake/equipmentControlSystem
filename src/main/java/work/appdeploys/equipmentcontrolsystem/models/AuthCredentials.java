package work.appdeploys.equipmentcontrolsystem.models;

import lombok.Data;

@Data
public class AuthCredentials {
    private Long id;
    private String email;
    private String password;
    private String roles;
}
