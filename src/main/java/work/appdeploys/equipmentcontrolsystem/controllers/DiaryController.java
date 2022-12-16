package work.appdeploys.equipmentcontrolsystem.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.DiarysResponse;
import work.appdeploys.equipmentcontrolsystem.services.DiaryService;

import javax.validation.Valid;
import java.util.Arrays;

@Tag(name="diary")
@RequiredArgsConstructor
@RequestMapping(value = "/api/diary/")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<DiarysResponse> save(@RequestBody @Valid DiaryDto diaryDto){
        try {
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_SAVED,Arrays.asList(diaryService.save(diaryDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<DiarysResponse> update(@RequestBody @Valid DiaryDto diaryDto) {
        try{
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_UPDATE,Arrays.asList(diaryService.update(diaryDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(),Arrays.asList()));
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<DiarysResponse> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_SELECTED,Arrays.asList(diaryService.findById(id))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(),Arrays.asList()));
        }
    }

    @GetMapping(path = "user/{idUser}")
    public ResponseEntity<DiarysResponse> getByIdUser(@PathVariable Long idUser) {
        try{
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_SELECTED_USER,diaryService.findByIdUser(idUser)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(),Arrays.asList()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DiarysResponse> delete(@PathVariable Long id) {
        try{
            diaryService.delete(id);
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_DELETED,Arrays.asList()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(),Arrays.asList()));

        }
    }

    @GetMapping()
    public ResponseEntity<DiarysResponse> findByAll(){
        try{
            return ResponseEntity.ok(new DiarysResponse(MessageResource.DIARY_LISTED, diaryService.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new DiarysResponse(ex.getMessage(),Arrays.asList()));
        }
    }




}
