package work.appdeploys.equipmentcontrolsystem.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;

import java.util.List;

@Data
@AllArgsConstructor
public class SchoolsResponse {
    private String message;
    private List<SchoolDto> schoolsDto;
}
