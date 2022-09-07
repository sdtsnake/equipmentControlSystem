package work.appDeploys.equipmentControlSystem.mappers;

import org.mapstruct.Mapper;
import work.appDeploys.equipmentControlSystem.models.Users;
import work.appDeploys.equipmentControlSystem.models.dtos.UsersDto;

// con esta notacion hacemos que le mapper sea un componente springboot
// Spring IoC
@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toModel(UsersDto dto);
    UsersDto toDto(Users users);
}
