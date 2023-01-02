package work.appdeploys.equipmentcontrolsystem.security.util;

public enum JwtConstant {
     AUTHORIZATION_HEADER_STRING ("Authorization"),
     TOKEN_BEARER_PREFIX ("Bearer "),
    ACCESS_TOKEN_EXPIRATION ("60");

    private String value;

    public String getValue()
    {
        return this.value;
    }
    JwtConstant(String value)
    {
        this.value = value;
    }

}
