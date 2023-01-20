package work.appdeploys.equipmentcontrolsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import work.appdeploys.equipmentcontrolsystem.security.JwtAuthenticationFilter;
import work.appdeploys.equipmentcontrolsystem.security.JwtAuthorizationFilter;
import work.appdeploys.equipmentcontrolsystem.security.JwtHelper;
import work.appdeploys.equipmentcontrolsystem.security.util.JwtUtil;
import work.appdeploys.equipmentcontrolsystem.servicesimpl.UserDetailServiceImpl;

@Configuration
public class WebSecurityConfig {

    private final UserDetailServiceImpl userDetailService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtHelper jwtHelper;
    private final JwtUtil jwtUtil;

    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetailService, JwtHelper jwtHelper, JwtUtil jwtUtil, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailService = userDetailService;
        this.jwtHelper = jwtHelper;
        this.jwtUtil = jwtUtil;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api-docs/**",
            "/api-docs",
            "/logout"

    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtHelper);
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/**").authenticated()
                .antMatchers("/api/users/**").access("hashRole('1')")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll())
                .build();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
