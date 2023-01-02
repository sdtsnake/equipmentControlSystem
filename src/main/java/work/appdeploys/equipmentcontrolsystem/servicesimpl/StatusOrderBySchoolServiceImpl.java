package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.mappers.StatusOrderBySchoolMapper;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.StatusOrderBySchool;
import work.appdeploys.equipmentcontrolsystem.models.structures.FileDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.StatusJasper;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.StatusOrderBySchoolRepository;
import work.appdeploys.equipmentcontrolsystem.services.StatusOrderBySchoolService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusOrderBySchoolServiceImpl implements StatusOrderBySchoolService {
    private final SchoolRepository schoolRepository;

    private final StatusOrderBySchoolMapper statusOrderBySchoolMapper;

    private final StatusOrderBySchoolRepository statusOrderBySchoolRepository;

    @Override
    public FileDto findByAllDateBySchool(LocalDate date, Long idSchool) throws FileNotFoundException, JRException {
        Optional<School> optSchool = schoolRepository.findById(idSchool);
        if(optSchool.isEmpty()){
            throw new StringIndexOutOfBoundsException(MessageResource.SCHOOLS_NOT_EXIST_RECORDS.getValue());
        }
        List<StatusOrderBySchool> listStatusOrder = statusOrderBySchoolRepository.findByDateCreate(date);
        if(listStatusOrder.isEmpty()){
            throw new StringIndexOutOfBoundsException(MessageResource.SCHOOL_STATUS_NOT_EXIST_RECORD.getValue());
        }

        List<StatusOrderBySchool> subListStatus = listStatusOrder.stream()
                .filter(statusOrderBySchool -> statusOrderBySchool.getIdSchool().equals(idSchool))
                .collect(Collectors.toList());

        if(subListStatus.isEmpty()){
            throw new StringIndexOutOfBoundsException(MessageResource.SCHOOL_STATUS_NOT_EXIST_RECORD.getValue());
        }
        //----
        FileDto filePdf = new FileDto();
        filePdf.setFile(generaPdf(statusOrderBySchoolMapper.toMapJasper(subListStatus)));
        filePdf.setNameFile(optSchool.get().getName().replaceAll(" ", "_"));
        return filePdf;
    }

    public byte[] generaPdf(List<StatusJasper> statusJasper) throws FileNotFoundException, JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(statusJasper);

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/equipementsystem.jrxml"));

        Map<String, Object> map = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
















