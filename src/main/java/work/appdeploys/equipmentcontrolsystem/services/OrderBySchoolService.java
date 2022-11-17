package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderBySchoolResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderBySchoolService {

    List<OrderBySchoolResponseDto> findByAllDate(LocalDate date);
    List<OrderBySchoolResponseDto> findByAllOrderSchool(long orderSchool);
}
