package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.ResponseSchools;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.SchoolServiceImpl;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Tag(name = "school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/school/")
@RestController
public class SchoolController {
    private final SchoolServiceImpl schoolServiceImpl;

    @PostMapping()
    public ResponseEntity<ResponseSchools> save(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            SchoolDto result = schoolServiceImpl.save(schoolDto);
            return ResponseEntity.ok(new ResponseSchools(MessageResource.SCHOOL_SAVED, Arrays.asList(result)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseSchools(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<ResponseSchools> update(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            SchoolDto result = schoolServiceImpl.update(schoolDto);
            return ResponseEntity.ok(new ResponseSchools(MessageResource.SCHOOL_UPDATE,Arrays.asList(result)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseSchools(ex.getMessage(),Arrays.asList()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseSchools> delete(@PathVariable Long id) {

        try{
            schoolServiceImpl.delete(id);
            return ResponseEntity.ok(new ResponseSchools(MessageResource.SCHOOL_DELETED,Arrays.asList()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseSchools(ex.getMessage(),Arrays.asList()));

        }
    }

    @GetMapping
    public ResponseEntity<ResponseSchools> findByAll(){
        try{
            return ResponseEntity.ok(new ResponseSchools(MessageResource.SCHOOL_LISTED,schoolServiceImpl.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new ResponseSchools(ex.getMessage(),Arrays.asList()));
        }
    }
}
