package work.appDeploys.equipmentControlSystem.services;

import work.appDeploys.equipmentControlSystem.models.dtos.UsersDto;

public interface UsersService {

    UsersDto save(UsersDto usersDto);

    void delete(Long id);

    UsersDto update(UsersDto usersDto);
}
