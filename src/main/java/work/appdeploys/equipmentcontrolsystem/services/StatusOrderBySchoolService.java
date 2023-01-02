package work.appdeploys.equipmentcontrolsystem.services;

import net.sf.jasperreports.engine.JRException;
import work.appdeploys.equipmentcontrolsystem.models.structures.FileDto;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface StatusOrderBySchoolService {
    FileDto findByAllDateBySchool(LocalDate date, Long idSchool) throws FileNotFoundException, JRException;
}
