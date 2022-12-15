package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.DiarysResponse;

import java.util.List;

public interface DiaryService {

    DiaryDto save(DiaryDto diaryDto);
    void delete(long id);
    DiarysResponse update(DiaryDto diaryDto);
    List<DiarysResponse> findByAll();

}
