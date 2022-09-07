package work.appDeploys.equipmentControlSystem.models.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "school")
public class SchoolDto {

    @Schema(description = "Coded of school", example = "1")
    private Long id;
    @Schema(description = "name of school", example = "Lancaster school superior")
    private String name;
}
