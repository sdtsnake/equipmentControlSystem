package work.appdeploys.equipmentcontrolsystem.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private byte[] file;
    private String nameFile;
}
