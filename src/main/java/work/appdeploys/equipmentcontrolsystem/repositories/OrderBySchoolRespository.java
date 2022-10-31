package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import work.appdeploys.equipmentcontrolsystem.models.OrdersBySchool;

import java.util.Date;
import java.util.List;

public interface OrderBySchoolRespository extends JpaRepository<OrdersBySchool, Long> {
    List<OrdersBySchool> findByOrderDate(Date date);
    List<OrdersBySchool> findByOrderSchool(Long orderSchool);
}
