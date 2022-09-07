package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;

public interface SchoolService {

    SchoolDto save(SchoolDto schoolDto);

    void delete(Long id);

    SchoolDto update(SchoolDto schoolDto);
}
