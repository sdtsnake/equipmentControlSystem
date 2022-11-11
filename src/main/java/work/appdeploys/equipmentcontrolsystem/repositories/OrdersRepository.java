package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import work.appdeploys.equipmentcontrolsystem.models.Orders;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByOrderNumber(Long orderNumber);
    List<Orders> findByDateCreate(LocalDate orderNumber);


}

