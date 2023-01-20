package work.appdeploys.equipmentcontrolsystem.services;

import org.springframework.security.core.userdetails.UserDetails;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.UsersResponseDto;

import java.util.List;

public interface UsersService {

    UserResponseDto save(UsersDto usersDto);

    void delete(Long id);

    UserResponseDto update(UsersDto usersDto);

    List<UserResponseDto> findByAll();

    UsersDto findById(Long id);

    UserResponseDto findByEmail(String email);
    UserDetails findOneByEmail(String email);
}
