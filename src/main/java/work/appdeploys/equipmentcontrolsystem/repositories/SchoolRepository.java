package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.appdeploys.equipmentcontrolsystem.models.School;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByName(String name);

}
