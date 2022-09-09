package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.SchoolServiceImpl;

import javax.validation.Valid;

@Tag(name = "school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/school/")
@RestController
public class SchoolController {
    private final SchoolServiceImpl schoolServiceImpl;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    SchoolDto save(@RequestBody @Valid SchoolDto schoolDto) {
        return schoolServiceImpl.save(schoolDto);
    }

    @PutMapping
    public @ResponseBody SchoolDto update(@RequestBody @Valid SchoolDto schoolDto) {
        return schoolServiceImpl.update(schoolDto);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        schoolServiceImpl.delete(id);
    }
}
