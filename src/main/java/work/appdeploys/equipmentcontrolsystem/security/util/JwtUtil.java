package work.appdeploys.equipmentcontrolsystem.security.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtConstant.AUTHORIZATION_HEADER_STRING);
        if (authHeader != null || authHeader.startsWith(JwtConstant.TOKEN_BEARER_PREFIX)) {
            return authHeader.replace(JwtConstant.TOKEN_BEARER_PREFIX, "");
        }
        return null;
    }


}
