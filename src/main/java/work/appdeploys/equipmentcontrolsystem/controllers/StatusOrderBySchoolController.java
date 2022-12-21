package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.services.StatusOrderBySchoolService;

import java.io.IOException;
import java.time.LocalDate;

@Tag(name="Status by schools")
@RequiredArgsConstructor
@RequestMapping(value = "/api/pdf/")
@RestController
public class StatusOrderBySchoolController {
    private final StatusOrderBySchoolService orderBySchoolService;

    @GetMapping(path = "{dateTo}/{idSchool}")
    public ResponseEntity<byte[]> statusSchoolPdf(@PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, @PathVariable("idSchool") Long idSchool) throws IOException, JRException {

        byte data[] = orderBySchoolService.findByAllDateBySchool(dateTo,idSchool);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename","dispositivos.pdf");


        return ResponseEntity.ok().headers(headers).body(data);
    }
}
