package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import work.appdeploys.equipmentcontrolsystem.models.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
