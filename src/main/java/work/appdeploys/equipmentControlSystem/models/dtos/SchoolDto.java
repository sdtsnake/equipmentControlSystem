package work.appdeploys.equipmentControlSystem.models.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "School")
public class SchoolDto {

    @Schema(description = "Codigo del colegio", example="1")
    private Long id;
    @Schema(description = "Nombre del colegio", example="Lancaster school superior")
    private String name;
}
