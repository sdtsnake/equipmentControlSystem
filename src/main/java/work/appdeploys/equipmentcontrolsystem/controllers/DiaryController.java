package work.appdeploys.equipmentcontrolsystem.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="diary")
@RequiredArgsConstructor
@RequestMapping(value = "/api/diary/")
@RestController
public class DiaryController {



}
