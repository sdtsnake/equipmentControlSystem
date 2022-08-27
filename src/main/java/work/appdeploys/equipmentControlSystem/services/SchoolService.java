package work.appdeploys.equipmentControlSystem.services;

import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;

public interface SchoolService {

    SchoolDto save(SchoolDto schoolDto);
    void delete(Long id);
    SchoolDto update(SchoolDto schoolDto);
}
