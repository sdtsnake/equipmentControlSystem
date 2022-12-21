package work.appdeploys.equipmentcontrolsystem.services;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface StatusOrderBySchoolService {
    byte[] findByAllDateBySchool(LocalDate date, Long idSchool) throws FileNotFoundException, JRException;
}
