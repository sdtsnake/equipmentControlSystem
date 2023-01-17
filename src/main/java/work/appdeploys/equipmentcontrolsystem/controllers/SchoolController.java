package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.SchoolsResponse;
import work.appdeploys.equipmentcontrolsystem.services.SchoolService;

import javax.validation.Valid;
import java.util.Arrays;

@Tag(name = "school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/school")
@RestController
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping()
    public ResponseEntity<SchoolsResponse> save(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_SAVED.getValue(), Arrays.asList(schoolService.save(schoolDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(), Arrays.asList()));
        }
    }
    @PutMapping()
    public ResponseEntity<SchoolsResponse> update(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_UPDATE.getValue(),Arrays.asList(schoolService.update(schoolDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<SchoolsResponse> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_SELECTED.getValue(),Arrays.asList(schoolService.findById(id))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));
        }
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SchoolsResponse> delete(@PathVariable Long id) {
        try{
            schoolService.delete(id);
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_DELETED.getValue(),Arrays.asList()));
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(MessageResource.SCHOOL_CONSTRAIN_VIOLATION.getValue(),Arrays.asList()));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));

        }
    }
    @GetMapping()
    public ResponseEntity<SchoolsResponse> findByAll(){
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOLS_LISTED.getValue(), schoolService.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));
        }
    }
}
