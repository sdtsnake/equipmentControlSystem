package work.appdeploys.equipmentControlSystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import work.appdeploys.equipmentControlSystem.models.dtos.School;
import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;

@Mapper
public interface SchoolMapper {
    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    School toModel(SchoolDto dto);
    SchoolDto toDto(School school);

}
