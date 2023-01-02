package work.appdeploys.equipmentcontrolsystem.mappers;

import org.springframework.stereotype.Component;
import work.appdeploys.equipmentcontrolsystem.models.StatusOrderBySchool;
import work.appdeploys.equipmentcontrolsystem.models.structures.StatusJasper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatusOrderBySchoolMapper {
    public List<StatusJasper> toMapJasper(List<StatusOrderBySchool> statusOrderBySchool){
        return statusOrderBySchool.stream().map(so ->  new StatusJasper(so.getNameSchool(),
                so.getStatusOrder(),
                Integer.parseInt(so.getQuantity())
        )).collect(Collectors.toList());
    }
}
