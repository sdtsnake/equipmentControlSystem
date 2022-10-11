package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDtoRequest;

import java.util.List;

public interface OrdersService {
    OrderResponseDto save(OrdersDtoRequest ordersDto);

    void delete(long id);

    OrderResponseDto update(OrdersDtoRequest ordersDto);

    List<OrderResponseDto> findByAll();
}
