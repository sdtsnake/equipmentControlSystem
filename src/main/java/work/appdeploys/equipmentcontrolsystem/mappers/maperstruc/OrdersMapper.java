package work.appdeploys.equipmentcontrolsystem.mappers.maperstruc;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrdersExcelDto;

// con esta notacion hacemos que le mapper sea un componente springboot
// Spring IoC
@Mapper(componentModel = "spring")
public interface OrdersMapper {

    Orders toModel(OrderResponseDto dto);
    OrderResponseDto toResponseDto(Orders orders);
    OrdersExcelDto toModelExcelDto(Orders orders);


}
