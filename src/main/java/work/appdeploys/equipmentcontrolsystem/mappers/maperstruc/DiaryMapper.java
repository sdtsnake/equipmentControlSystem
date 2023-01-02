package work.appdeploys.equipmentcontrolsystem.mappers.maperstruc;

import org.mapstruct.Mapper;
import work.appdeploys.equipmentcontrolsystem.models.Diary;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;

// con esta notacion hacemos que le mapper sea un componente springboot
// Spring IoC
@Mapper(componentModel = "spring")
public interface  DiaryMapper {
    Diary toModel(DiaryDto dto);

    DiaryDto toDto(Diary diary);

}
