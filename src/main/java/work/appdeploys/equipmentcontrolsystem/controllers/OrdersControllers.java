package work.appdeploys.equipmentcontrolsystem.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
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
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersRequestDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.ExcelDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.OrdersResponse;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;

@Tag(name="ordes")
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders/")
@RestController
public class OrdersControllers {
    private final OrdersService ordersService;

    @PostMapping()
    public ResponseEntity<OrdersResponse> save(@RequestBody @Valid OrdersRequestDto ordersRequestDto){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_SAVED, Arrays.asList(ordersService.save(ordersRequestDto))));
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(new OrdersResponse(ex.getMessage(), Arrays.asList()));
        }
    }

    @PutMapping
    public ResponseEntity<OrdersResponse> update(@RequestBody @Valid OrdersRequestDto ordersRequestDto){
        try{
            return ResponseEntity.ok(new OrdersResponse(MessageResource.ORDER_UPDATED,Arrays.asList(ordersService.update(ordersRequestDto))));
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
        try {
            ExcelDto excelDto = ordersService.excelOrders(dateTo, idSchool);
            String headerVal = "attachment; filename=" + excelDto.getNameExcel();
            response.setHeader("Content-Disposition", headerVal);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
            Files.copy(excelDto.getTmpExcel().toPath(), response.getOutputStream());
            try {
                excelDto.getTmpExcel().delete();
            }catch (Exception e){
                //TODO no importa porque ya se envio el archivo.
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
