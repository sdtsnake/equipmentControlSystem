package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.appdeploys.equipmentcontrolsystem.models.Orders;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByOrderNumber(Long orderNumber);
    List<Orders> findByDateCreate(LocalDate orderNumber);


}

