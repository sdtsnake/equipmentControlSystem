package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrderBySchoolResponse;
import work.appdeploys.equipmentcontrolsystem.services.OrderBySchoolService;

import java.util.Arrays;
import java.util.Date;

@Tag(name="Order numbrer by school")
@RequiredArgsConstructor
@RequestMapping(value = "/api/ordersnumber/")
@RestController
public class OrderBySchoolController {
    private final OrderBySchoolService orderBySchoolService;

    @GetMapping(path = "/date/{date}")
    public ResponseEntity<OrderBySchoolResponse> getByOrderNumberDate(@PathVariable Date date){
        try{
            return ResponseEntity.ok(new OrderBySchoolResponse(MessageResource.ORDER_NUMBER_LISTED,orderBySchoolService.findByAllDate(date)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrderBySchoolResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @GetMapping(path = "/school/{idschool}")
    public ResponseEntity<OrderBySchoolResponse> getByOrderNumberDate(@PathVariable Long id){
        try{
            return ResponseEntity.ok(new OrderBySchoolResponse(MessageResource.ORDER_NUMBER_LISTED,orderBySchoolService.findByAllOrderSchool(id)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrderBySchoolResponse(ex.getMessage(), Arrays.asList()));
        }
    }


}
