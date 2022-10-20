package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.OrdersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersRequestDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrdersRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public OrderResponseDto save(OrdersRequestDto ordersRequestDto) {
        Optional<School> school = schoolRepository.findById(ordersRequestDto.getIdschool());
        if(school.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.SCHOOL_EXIST_NOT_SAVE);
        }

        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        dateValidator(ordersRequestDto.getDateCreate().toString(), MessageResource.ORDER_DATE_INVALID_NOT_SAVE);

        Orders orders;
        try{
            orders = ordersMapper.toModel(ordersRequestDto);
            orders.setSchool(school.get());
            orders.setId(null);
            orders = ordersRepository.save(orders);
        }catch (Exception ex){
            throw new OrdersExceptionBadRequest(ex.getMessage());
        }
        return ordersMapper.toResponseDto(orders);
    }

    @Override
    public void delete(long id) {
        validateOrderById(id,MessageResource.ORDER_NOT_EXIST_NOT_DELETE);
        ordersRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public OrderResponseDto update(OrdersRequestDto ordersRequestDto) {
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        validateOrderById(ordersRequestDto.getId(), MessageResource.ORDER_NOT_EXIST_NOT_UPDATE);
        dateValidator(ordersRequestDto.getDateCreate().toString(), MessageResource.DATA_USER_CREATE_NOT_VALID_NOT_UPDATE);
        return ordersMapper.toResponseDto(ordersRepository.save(ordersMapper.toModel(ordersRequestDto)));
    }

    @Override
    public List<OrderResponseDto> findByAll(){
        List<Orders> list = ordersRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(ordersMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NOT_EXIST_RECORD);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Optional<Orders> optionOrdder = ordersRepository.findById(id);
        if(optionOrdder.isPresent()){
            return optionOrdder.map(ordersMapper::toResponseDto).get();
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NOT_EXIST_RECORD);
    }

    @Override
    public List<OrderResponseDto> findByAllOrderNumber(Long orderNumber) {
        List<Orders> optionOrdder = ordersRepository.findByOrderNumber(orderNumber);
        if(!optionOrdder.isEmpty()){
            return optionOrdder.stream().map(ordersMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD);
    }

    private void validateUsersById(Long id, String message) {
        usersRepository.findById(id).orElseThrow(() -> new UsersExceptionBadRequest(message));
    }

    private void validateOrderById(Long id, String message) {
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
