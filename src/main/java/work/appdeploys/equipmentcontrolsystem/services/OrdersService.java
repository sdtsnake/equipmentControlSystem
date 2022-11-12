package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersRequestDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrderExcelDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrdersExcelDto;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

public interface OrdersService {
    OrderResponseDto save(OrdersRequestDto ordersDto);

    void delete(long id);

    OrderResponseDto update(OrdersRequestDto ordersDto);

    List<OrderResponseDto> findByAll();

    OrderResponseDto findById(Long id);

    List<OrderResponseDto> findByAllOrderNumber(Long orderNumber);

    OrderExcelDto ExcelOrders(LocalDate dateTo, Long ordernuber) throws IOException;

}
