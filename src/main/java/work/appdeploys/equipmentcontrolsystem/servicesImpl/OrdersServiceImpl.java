package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.OrderResponseMapper;
import work.appdeploys.equipmentcontrolsystem.mappers.OrdersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrdersRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;

    @Override
    public OrderResponseDto save(OrdersDto ordersDto) {
        validateUsersById(ordersDto.getIdusermod().getId(),MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersDto.getIdusermod().getId(),MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        dateValidator(ordersDto.getDatecreate().toString(),MessageResource.ORDER_DATE_INVALID_NOT_SAVE);

        if(ordersRepository.findById(ordersDto.getId()).isPresent()){
            throw new OrdersExceptionBadRequest(MessageResource.ORDER_ALREADY_EXIST_NOT_SAVE);
        }
        return orderResponseMapper.toDto(ordersRepository.save(ordersMapper.toModel(ordersDto)));
    }

    @Override
    public void delete(long id) {
        validateOrderById(id,MessageResource.ORDER_NOT_EXIST_NOT_DELETE);
        ordersRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public OrderResponseDto update(OrdersDto ordersDto) {
        validateUsersById(ordersDto.getIdusermod().getId(),MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersDto.getIdusermod().getId(),MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        validateOrderById(ordersDto.getId(),MessageResource.ORDER_NOT_EXIST_NOT_UPDATE);
        dateValidator(ordersDto.getDatecreate().toString(),MessageResource.DATA_USER_CREATE_NOT_VALID_NOT_UPDATE);
        return orderResponseMapper.toDto(ordersRepository.save(ordersMapper.toModel(ordersDto)));
    }

    @Override
    public List<OrderResponseDto> findByAll(){
        List<Orders> list = ordersRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(orderResponseMapper::toDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NOT_EXIST_RECORD);
    }

    private void validateUsersById(Long id, String message) {
        usersRepository.findById(id).orElseThrow(() -> new UsersExceptionBadRequest(message));
    }

    private void validateOrderById(Long id, String message) {
        ordersRepository.findById(id).orElseThrow(() -> new OrdersExceptionBadRequest(message));
    }
    private void validateDate(Long id, String message) {
        ordersRepository.findById(id).orElseThrow(() -> new OrdersExceptionBadRequest(message));
    }

    public void dateValidator(String date, String message)
    {
        DateValidator validator = DateValidator.getInstance();
        if(!validator.isValid(date,"YYYY-MM-dd", Locale.ENGLISH)){
            throw new OrdersExceptionBadRequest(message);
        }
    }
}
