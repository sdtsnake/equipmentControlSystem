package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto save(UsersDto usersDto);

    void delete(Long id);

    UsersDto update(UsersDto usersDto);

    List<UsersDto> findByAll();
}
