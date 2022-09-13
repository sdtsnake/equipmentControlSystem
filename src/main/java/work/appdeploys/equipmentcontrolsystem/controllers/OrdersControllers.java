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
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDto;
import work.appdeploys.equipmentcontrolsystem.servicesImpl.OrdersServiceImpl;

import javax.validation.Valid;

@Tag(name="ordes")
@RequiredArgsConstructor
@RequestMapping(value = "/api/orders/")
@RestController
public class OrdersControllers {
    private final OrdersServiceImpl ordersServiceImpl;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody OrdersDto save(@RequestBody @Valid OrdersDto ordersDto){
        return ordersServiceImpl.save(ordersDto);
    }

    @PutMapping
    public @ResponseBody OrdersDto update(@RequestBody @Valid OrdersDto ordersDto){
        return ordersServiceImpl.update(ordersDto);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody void delete(@PathVariable long id){
        ordersServiceImpl.delete(id);
    }

}
