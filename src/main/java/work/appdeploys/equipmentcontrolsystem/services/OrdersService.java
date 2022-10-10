package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;

import java.util.List;

public interface OrdersService {
    OrdersDto save(OrdersDto ordersDto);

    void delete(long id);

    OrdersDto update(OrdersDto ordersDto);

    List<OrdersDto> findByAll();
}
