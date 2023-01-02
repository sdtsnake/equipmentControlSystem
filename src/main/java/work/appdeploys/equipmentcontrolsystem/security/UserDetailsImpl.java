package work.appdeploys.equipmentcontrolsystem.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import work.appdeploys.equipmentcontrolsystem.models.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(users.getRol()));
        return roles;
    }
    @Override
    public String getPassword() {
        return users.getPasswd();
    }
    @Override
    public String getUsername() {
        return users.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getName() {
        return users.getName();
    }
    public String getRoles() {
        return users.getRol();
    }
    public Long getId() {
        return users.getId();
    }
}
