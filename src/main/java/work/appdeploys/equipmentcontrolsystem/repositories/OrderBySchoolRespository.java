package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import work.appdeploys.equipmentcontrolsystem.models.OrdersBySchool;

import java.time.LocalDate;
import java.util.List;

public interface OrderBySchoolRespository extends JpaRepository<OrdersBySchool, Long> {
    List<OrdersBySchool> findByOrderDate(LocalDate date);
    List<OrdersBySchool> findByOrderSchool(Long orderSchool);
}
