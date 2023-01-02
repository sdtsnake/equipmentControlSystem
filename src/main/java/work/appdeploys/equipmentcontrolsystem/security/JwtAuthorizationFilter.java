package work.appdeploys.equipmentcontrolsystem.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.AuthorizationFilterException;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtConstant;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;

    private final JwtUtil jwtUtil;

    public JwtAuthorizationFilter(JwtHelper jwtHelper,JwtUtil jwtUtil) {
        this.jwtHelper = jwtHelper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(JwtConstant.AUTHORIZATION_HEADER_STRING.getValue());
        if(bearerToken != null && bearerToken.startsWith(JwtConstant.TOKEN_BEARER_PREFIX.getValue())){
            UsernamePasswordAuthenticationToken usernamePAT = geAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        filterChain.doFilter(request, response);
    }

    public  UsernamePasswordAuthenticationToken geAuthentication(HttpServletRequest request){
        try {
            String token = this.jwtUtil.extractToken(request);
            if (token != null) {
                Claims claims = this.jwtHelper.validateToken(token);
                String email = claims.getSubject();
                return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
                }
        } catch (Exception e) {
            throw new AuthorizationFilterException(MessageResource.TOKEN_NOT_EXIST);
        }

        return null;
    }
}
