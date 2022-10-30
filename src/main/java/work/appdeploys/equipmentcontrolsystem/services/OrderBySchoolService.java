package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderBySchoolResponseDto;

import java.util.Date;
import java.util.List;

public interface OrderBySchoolService {

    List<OrderBySchoolResponseDto> findByAllDate(Date date);
    List<OrderBySchoolResponseDto> findByAllOrderSchool(long orderSchool);
}
