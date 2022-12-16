package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.ExcelDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface OrdersService {
    OrderResponseDto save(OrderResponseDto ordersDto);

    void delete(long id);

    OrderResponseDto update(OrderResponseDto ordersDto);

    List<OrderResponseDto> findByAll();

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> findByAllOrderNumber(Long orderNumber);

    ExcelDto excelOrders(LocalDate dateTo, Long ordernuber) throws IOException;

}
