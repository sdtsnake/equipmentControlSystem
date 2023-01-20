package work.appdeploys.equipmentcontrolsystem.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersResponseDto {
    private String message;
    private List<UsersDto> usersDto;
}
