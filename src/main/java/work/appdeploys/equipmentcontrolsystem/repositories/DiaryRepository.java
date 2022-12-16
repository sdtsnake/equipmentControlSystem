package work.appdeploys.equipmentcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work.appdeploys.equipmentcontrolsystem.models.Diary;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(Long iduser);

}
