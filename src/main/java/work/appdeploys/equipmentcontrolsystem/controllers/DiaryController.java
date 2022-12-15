package work.appdeploys.equipmentcontrolsystem.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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





}
