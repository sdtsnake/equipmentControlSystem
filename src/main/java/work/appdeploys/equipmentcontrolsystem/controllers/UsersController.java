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
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.UsersServiceImpl;

import javax.validation.Valid;

@Tag(name="users")
@RequiredArgsConstructor
@RequestMapping(value = "/api/users/")
@RestController
public class UsersController {
    private final UsersServiceImpl usersServiceImpl;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    UsersDto save(@RequestBody @Valid UsersDto usersDto){
        return usersServiceImpl.save(usersDto);
    }

    @PutMapping
    public @ResponseBody UsersDto update(@RequestBody @Valid UsersDto usersDto){
        return usersServiceImpl.update(usersDto);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void delete(@PathVariable Long id){
        usersServiceImpl.delete(id);
    }
}
