package work.appdeploys.equipmentcontrolsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import work.appdeploys.equipmentcontrolsystem.models.AuthCredentials;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtConstant;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtUtil jwtUtil;

    private JwtHelper jwtHelper;

    public JwtAuthenticationFilter( JwtHelper jwtHelper, JwtUtil jwtUtil) {
        this.jwtHelper = jwtHelper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        } catch (IOException e) {
            log.error("Error on validation user");
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = this.jwtHelper.createToken(userDetails.getName(),userDetails.getUsername(),userDetails.getId(),userDetails.getRoles());
        response.addHeader(JwtConstant.AUTHORIZATION_HEADER_STRING,JwtConstant.TOKEN_BEARER_PREFIX + token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
