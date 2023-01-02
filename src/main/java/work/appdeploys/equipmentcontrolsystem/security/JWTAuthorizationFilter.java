package work.appdeploys.equipmentcontrolsystem.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO segun el video aqui se carga lo de los roles vamos implementar todo segun el video y despues vamos a incluir el manejo de roles pero este es el punto.
        String barearToken = request.getHeader("Authorization");
        if(barearToken != null && barearToken.startsWith("Bearer ")){
            String token = barearToken.replace("Bearer ","");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.geAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        filterChain.doFilter(request, response);
    }
}
