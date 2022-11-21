package work.appdeploys.equipmentcontrolsystem.models.structures;


import lombok.AllArgsConstructor;
import lombok.Data;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderBySchoolResponseDto;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderBySchoolResponse {
    private String message;
    private List<OrderBySchoolResponseDto> orderBySchoolResponseDto;
}
