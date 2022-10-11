package work.appdeploys.equipmentcontrolsystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    //UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);
    //@Mapping(target = "passwd", ignore = true)
    UserResponseDto toDto(Users users);
}
