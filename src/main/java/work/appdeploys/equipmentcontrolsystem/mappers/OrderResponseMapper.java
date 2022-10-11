package work.appdeploys.equipmentcontrolsystem.mappers;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    OrderResponseDto toDto(Orders orders);
}
