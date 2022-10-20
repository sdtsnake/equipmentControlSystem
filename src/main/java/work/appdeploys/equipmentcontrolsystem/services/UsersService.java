package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;

import java.util.List;

public interface UsersService {

    UserResponseDto save(UsersDto usersDto);

    void delete(Long id);

    UserResponseDto update(UsersDto usersDto);

    List<UserResponseDto> findByAll();

    UserResponseDto findById(Long id);

    UserResponseDto findByEmail(String email);
}
