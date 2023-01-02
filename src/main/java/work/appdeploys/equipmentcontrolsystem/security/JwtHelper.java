package work.appdeploys.equipmentcontrolsystem.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtConstant;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    @Value("${SECRET}")
    private String secret;


    public   String createToken(String name , String email, Long id, String rol) {
        if (email == null) {
            throw new NullPointerException("Error generating access token, username is null");
        }
        // Expiration.
        Date expiration = generateTokenExp();
        // Issue date.
        Date current = new Date();
        Map<String, Object> extra = getStringObjectMap(name, email, id, rol);
        // Generate token.
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(current)
                .setExpiration(expiration)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private static Map<String, Object> getStringObjectMap(String name, String email, Long id, String rol) {
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        extra.put("email", email);
        extra.put("id", id);
        extra.put("rol", rol);
        return extra;
    }

    private Date generateTokenExp() {
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.MINUTE, Integer.parseInt(JwtConstant.ACCESS_TOKEN_EXPIRATION.getValue()));
        return calendar.getTime();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
