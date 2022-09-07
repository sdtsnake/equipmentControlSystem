package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;

public interface UsersService {

    UsersDto save(UsersDto usersDto);

    void delete(Long id);

    UsersDto update(UsersDto usersDto);
}
