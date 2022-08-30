package work.appdeploys.equipmentControlSystem.mappers;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentControlSystem.models.Users;
import work.appdeploys.equipmentControlSystem.models.dtos.UsersDto;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toModel(UsersDto dto);
    UsersDto toDto(Users users);
}
