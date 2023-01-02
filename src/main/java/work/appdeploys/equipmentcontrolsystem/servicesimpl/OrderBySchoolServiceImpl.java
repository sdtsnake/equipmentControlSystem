package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.OrderBySchoolMapper;
import work.appdeploys.equipmentcontrolsystem.models.OrdersBySchool;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderBySchoolResponseDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrderBySchoolRespository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrderBySchoolService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderBySchoolServiceImpl implements OrderBySchoolService {
    private final OrderBySchoolMapper orderBySchoolMapper;
    private final SchoolRepository schoolRepository;
    private final OrderBySchoolRespository orderBySchoolRespository;

    @Override
    public List<OrderBySchoolResponseDto> findByAllDate(LocalDate date) {
        List<OrdersBySchool> listOrdersBySchool = orderBySchoolRespository.findByOrderDate(date);
        if(!listOrdersBySchool.isEmpty()){
            return listOrdersBySchool.stream().map(orderBySchoolMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD_DATE.getValue());
    }

    @Override
    public List<OrderBySchoolResponseDto> findByAllOrderSchool(long orderSchool) {
        Optional<School> school = schoolRepository.findById(orderSchool);
        if(school.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.SCHOOLS_NOT_EXIST_RECORDS.getValue());
        }
        List<OrdersBySchool> listOrdersBySchool = orderBySchoolRespository.findByOrderSchool(orderSchool);
        if(!listOrdersBySchool.isEmpty()){
            return listOrdersBySchool.stream().map(orderBySchoolMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD.getValue());
    }
}
