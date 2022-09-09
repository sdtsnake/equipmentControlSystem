package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.mappers.OrdersMapper;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrdersRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;

    @Override
    public OrdersDto save(OrdersDto ordersDto) {
        return ordersMapper.toDto(ordersRepository.save(ordersMapper.toModel(ordersDto)));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public OrdersDto update(UsersDto usersDto) {
        return null;
    }
}
