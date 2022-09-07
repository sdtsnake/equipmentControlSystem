package work.appDeploys.equipmentControlSystem.mappers;

import org.mapstruct.Mapper;
import work.appDeploys.equipmentControlSystem.models.School;
import work.appDeploys.equipmentControlSystem.models.dtos.SchoolDto;

// con esta notacion hacemos que le mapper sea un componente springboot
// Spring IoC
@Mapper(componentModel = "spring")
public interface SchoolMapper {

    School toModel(SchoolDto dto);
    SchoolDto toDto(School school);
}
