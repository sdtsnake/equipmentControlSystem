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
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.SchoolsResponse;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.SchoolServiceImpl;

import javax.validation.Valid;
import java.util.Arrays;

@Tag(name = "school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/school/")
@RestController
public class SchoolController {
    private final SchoolServiceImpl schoolServiceImpl;

    @PostMapping()
    public ResponseEntity<SchoolsResponse> save(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_SAVED, Arrays.asList(schoolServiceImpl.save(schoolDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<SchoolsResponse> update(@RequestBody @Valid SchoolDto schoolDto) {
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_UPDATE,Arrays.asList(schoolServiceImpl.update(schoolDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SchoolsResponse> delete(@PathVariable Long id) {
        try{
            schoolServiceImpl.delete(id);
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOL_DELETED,Arrays.asList()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));

        }
    }

    @GetMapping
    public ResponseEntity<SchoolsResponse> findByAll(){
        try{
            return ResponseEntity.ok(new SchoolsResponse(MessageResource.SCHOOLS_LISTED,schoolServiceImpl.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new SchoolsResponse(ex.getMessage(),Arrays.asList()));
        }
    }
}
