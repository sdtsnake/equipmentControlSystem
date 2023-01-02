package work.appdeploys.equipmentcontrolsystem.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private Long id;
    private String email;
    private String password;
    private String roles;
}
