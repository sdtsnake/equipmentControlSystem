package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import work.appdeploys.equipmentcontrolsystem.models.StatusOrderBySchool;

import java.time.LocalDate;
import java.util.List;

public interface StatusOrderBySchoolRepository extends JpaRepository<StatusOrderBySchool, Long> {

    List<StatusOrderBySchool> findByDateCreate(LocalDate date);
}
