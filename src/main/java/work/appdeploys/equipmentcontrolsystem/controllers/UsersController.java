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
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.UsersResponse;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.UsersServiceImpl;

import javax.validation.Valid;
import java.util.Arrays;

@Tag(name="users")
@RequiredArgsConstructor
@RequestMapping(value = "/api/users/")
@RestController
public class UsersController {
    private final UsersServiceImpl usersServiceImpl;

    @PostMapping()
    public ResponseEntity<UsersResponse> save(@RequestBody @Valid UsersDto usersDto){
        try{
            return ResponseEntity.ok(new UsersResponse(MessageResource.USER_SAVED, Arrays.asList(usersServiceImpl.save(usersDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new UsersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<UsersResponse> update(@RequestBody @Valid UsersDto usersDto){
        try{
            return ResponseEntity.ok(new UsersResponse(MessageResource.USER_UPDATED, Arrays.asList(usersServiceImpl.update(usersDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new UsersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UsersResponse> delete(@PathVariable Long id){
        try{
            usersServiceImpl.delete(id);
            return ResponseEntity.ok(new UsersResponse(MessageResource.USER_DELETED,Arrays.asList()));
        }catch (Exception ex){
            return ResponseEntity.ok(new UsersResponse(ex.getMessage(), Arrays.asList()));
        }

    }

    @GetMapping
    public ResponseEntity<UsersResponse> findByAll(){
        try{
            return ResponseEntity.ok(new UsersResponse(MessageResource.USERS_LISTED,usersServiceImpl.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.ok(new UsersResponse(ex.getMessage(), Arrays.asList()));
        }
    }


}
