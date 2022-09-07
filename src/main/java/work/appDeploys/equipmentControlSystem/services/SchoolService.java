package work.appDeploys.equipmentControlSystem.services;

import work.appDeploys.equipmentControlSystem.models.dtos.SchoolDto;

public interface SchoolService {

    SchoolDto save(SchoolDto schoolDto);

    void delete(Long id);

    SchoolDto update(SchoolDto schoolDto);
}
