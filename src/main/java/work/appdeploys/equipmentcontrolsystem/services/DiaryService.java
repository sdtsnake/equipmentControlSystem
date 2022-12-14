package work.appdeploys.equipmentcontrolsystem.services;

import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.DiaryResponse;

public interface DiaryService {

    DiaryResponse save(DiaryDto diaryDto);
}
