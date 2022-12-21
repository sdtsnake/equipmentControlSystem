package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.ExcelDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrdersResponse;
import work.appdeploys.equipmentcontrolsystem.services.FileCopy;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
@Tag(name="ordes")
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders/")
@RestController
@Slf4j
public class OrdersControllers {
    private final OrdersService ordersService;
    private final FileCopy fileCopy;

    @PostMapping()
    public ResponseEntity<OrdersResponse> save(@RequestBody @Valid OrderResponseDto orderResponseDto){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_SAVED, Arrays.asList(ordersService.save(orderResponseDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<OrdersResponse> update(@RequestBody @Valid OrderResponseDto orderResponseDto){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_UPDATED,Arrays.asList(ordersService.update(orderResponseDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<OrdersResponse> delete(@PathVariable long id){
        ordersService.delete(id);
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDERS_DELETED, Arrays.asList()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @GetMapping
    public ResponseEntity<OrdersResponse> findByAll(){
        try {
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDERS_LISTED, ordersService.findByAll()));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(),Arrays.asList()));
        }
    }

    @GetMapping(path = "/ordernumber/{orderNumber}")
    public ResponseEntity<OrdersResponse> getByOrderNumber(@PathVariable Long orderNumber){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_NUMBER_LISTED,ordersService.findByAllOrderNumber(orderNumber)));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrdersResponse> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_LISTED,Arrays.asList(ordersService.findById(id))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @GetMapping(path = "/excelorders/{dateTo}/{idSchool}")
    public void ordersExceclRespor(HttpServletResponse response, @PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo, @PathVariable("idSchool") Long idSchool) throws IOException {
        ExcelDto excelDto = null;
        try {
            excelDto = ordersService.excelOrders(dateTo, idSchool);
            String headerVal = "attachment; filename=" + excelDto.getNameExcel();
            response.setHeader("Content-Disposition", headerVal);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
            fileCopy.copyFile(excelDto.getTmpExcel(), response.getOutputStream());

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }finally {
            try {
                if(excelDto != null){
                    excelDto.getTmpExcel().delete();
                }
            }catch (Exception e){
                log.error("Error delete file temporal");
            }
        }
    }
}
