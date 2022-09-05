package work.appdeploys.equipmentControlSystem.services;

import work.appdeploys.equipmentControlSystem.models.dtos.UsersDto;

public interface UsersService {

    UsersDto save(UsersDto usersDto);

    void delete(Long id);

    UsersDto update(UsersDto usersDto);
}
