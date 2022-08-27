package work.appdeploys.equipmentControlSystem.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentControlSystem.servicesImpl.SchoolServiceImpl;

import javax.validation.Valid;

@Tag(name="school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/")
@RestController
public class SchoolController {
    private final SchoolServiceImpl schoolServiceImpl;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody SchoolDto save(@RequestBody @Valid SchoolDto schoolDto){
           return schoolServiceImpl.save(schoolDto);
    }

    @PutMapping
    public @ResponseBody SchoolDto update(@RequestBody @Valid SchoolDto schoolDto){
        return schoolServiceImpl.update(schoolDto);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void delete(Long id){
        schoolServiceImpl.delete(id);
    }
}
