package work.appdeploys.equipmentControlSystem.mappers;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentControlSystem.models.Users;
import work.appdeploys.equipmentControlSystem.models.dtos.UsersDto;

// con esta notacion hacemos que le mapper sea un componente springboot
// Spring IoC
@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toModel(UsersDto dto);
    UsersDto toDto(Users users);
}
