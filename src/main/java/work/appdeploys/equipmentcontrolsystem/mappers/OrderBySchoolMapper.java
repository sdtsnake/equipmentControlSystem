package work.appdeploys.equipmentcontrolsystem.mappers;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentcontrolsystem.models.OrdersBySchool;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderBySchoolResponseDto;

@Mapper(componentModel = "spring")
public interface OrderBySchoolMapper {
    OrderBySchoolResponseDto toResponseDto(OrdersBySchool ordersBySchool);
}
