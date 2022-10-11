package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDto;

import java.util.List;

public interface OrdersService {
    OrderResponseDto save(OrdersDto ordersDto);

    void delete(long id);

    OrderResponseDto update(OrdersDto ordersDto);

    List<OrderResponseDto> findByAll();
}
