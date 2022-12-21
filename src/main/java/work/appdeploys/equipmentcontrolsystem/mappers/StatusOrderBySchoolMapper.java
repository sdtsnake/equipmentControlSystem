package work.appdeploys.equipmentcontrolsystem.mappers;

import org.springframework.stereotype.Component;
import work.appdeploys.equipmentcontrolsystem.models.StatusOrderBySchool;
import work.appdeploys.equipmentcontrolsystem.models.dtos.StatusOrderBySchoolResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.StatusJasper;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatusOrderBySchoolMapper {

    public List<StatusJasper> toMapJasper(List<StatusOrderBySchool> statusOrderBySchool){
        List<StatusJasper> statusJasper = new ArrayList<>();
        StatusJasper sj = new StatusJasper();

        for (StatusOrderBySchool so: statusOrderBySchool) {
            sj.setNameSchool(so.getNameSchool());
            sj.setStatusOrder(so.getStatusOrder());
            sj.setQuantity(Integer.parseInt(so.getQuantity()));
            statusJasper.add(sj);
        }
        return statusJasper;
    }
}
