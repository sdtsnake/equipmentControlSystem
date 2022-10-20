package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;

import java.util.List;

public interface SchoolService {

    SchoolDto save(SchoolDto schoolDto);

    void delete(Long id);

    SchoolDto update(SchoolDto schoolDto);

    List<SchoolDto> findByAll();

    SchoolDto findById(Long id);



}
