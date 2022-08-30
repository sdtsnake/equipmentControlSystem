package work.appdeploys.equipmentControlSystem.services;

import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentControlSystem.models.dtos.UsersDto;

public interface UsersRepository {

    UsersDto save(UsersDto usersDto);
    void delete(Long id);
    UsersDto update(SchoolDto schoolDto);
}
