package work.appdeploys.equipmentcontrolsystem.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@Data
@Schema(description = "diary")
@Builder
@EqualsAndHashCode(callSuper=false)
public class DiaryDto {

    @Schema(description = "code diary", example="1")
    private Long id;

    @Schema(description = "user assigned to visit the school", example = "2")
    private UserResponseDto user;

    @Schema(description = "school to visit", example = "get de de id school and name has JSON")
    private SchoolDto school;

    @Schema(description = "day assigned for the visit valuer 1 at 7", example = "1")
    private int weekday;

    @Schema(description = "start time", example = "10:15:00")
    private LocalTime startTime;

    @Schema(description = "ending time", example = "14:40:00")
    private LocalTime endingTime;

    @Schema(description = "if you are doing a replacement send an 1 otherwise send 0", example = "1")
    private String replacement;

}
