package work.appdeploys.equipmentcontrolsystem.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;

import java.util.List;

@Data
@AllArgsConstructor
public class DiarysResponse {
    private String message;
    private List<DiaryDto> diaryDto;

}
