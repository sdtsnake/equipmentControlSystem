package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.security.UserDetailsImpl;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository
                .findByEmail(email)
                .orElseThrow(()-> new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS_EMAIL));
        return new UserDetailsImpl(users);
    }
}
