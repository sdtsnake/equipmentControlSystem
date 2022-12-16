package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;

import java.util.List;

public interface DiaryService {

    DiaryDto save(DiaryDto diaryDto);
    void delete(long id);
    DiaryDto update(DiaryDto diaryDto);
    List<DiaryDto> findByAll();
    DiaryDto findById(Long id);
    List<DiaryDto> findByIdUser(Long idUser);

}
