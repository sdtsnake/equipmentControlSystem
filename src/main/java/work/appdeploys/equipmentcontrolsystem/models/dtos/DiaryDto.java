package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "diary")
public class DiaryDto {

    @Schema(description = "code diary", example="1")
    private Long id;

    @Schema(description = "user assigned to visit the school", example = "2")
    private UserResponseDto userResponseDto;

    @Schema(description = "school to visit", example = "get de de id school and name has JSON")
    private SchoolDto schoolDto;

    @Schema(description = "day assigned for the visit", example = "MONDAY")
    private String weekday;

    @Schema(description = "start time", example = "10:15:00")
    private LocalTime startTime;

    @Schema(description = "ending time", example = "14:40:00")
    private LocalTime endingTime;

    @Schema(description = "if you are doing a replacement send an X otherwise send white", example = "X")
    private String replacement;

}
