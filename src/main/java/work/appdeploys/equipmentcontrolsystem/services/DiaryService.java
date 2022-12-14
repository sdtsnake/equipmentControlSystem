package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.DiaryResponse;

import java.util.List;

public interface DiaryService {

    DiaryResponse save(DiaryDto diaryDto);
    void delete(long id);
    DiaryResponse update(DiaryDto diaryDto);
    List<DiaryResponse> findByAll();

}
