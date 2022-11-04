package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nonapi.io.github.classgraph.fileslice.ArraySlice;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.OrdersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersRequestDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrdersRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final SchoolRepository schoolRepository;
    private final String [] encabezados= {"Order","Model Number","Serial Number","Tag Asset","Issue","Incident #","Repair","Status"};

    @Override
    public OrderResponseDto save(OrdersRequestDto ordersRequestDto) {
        Optional<School> school = schoolRepository.findById(ordersRequestDto.getIdschool());
        if(school.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.SCHOOL_EXIST_NOT_SAVE);
        }

        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        dateValidator(ordersRequestDto.getDateCreate().toString(), MessageResource.ORDER_DATE_INVALID_NOT_SAVE);

        Orders orders;
        try{
            orders = ordersMapper.toModel(ordersRequestDto);
            orders.setSchool(school.get());
            orders.setId(null);
            orders = ordersRepository.save(orders);
        }catch (Exception ex){
            throw new OrdersExceptionBadRequest(ex.getMessage());
        }
        return ordersMapper.toResponseDto(orders);
    }

    @Override
    public void delete(long id) {
        validateOrderById(id,MessageResource.ORDER_NOT_EXIST_NOT_DELETE);
        ordersRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public OrderResponseDto update(OrdersRequestDto ordersRequestDto) {
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE);
        validateUsersById(ordersRequestDto.getIdUserMod().getId(), MessageResource.USER_MOD_ORDER_NOT_EXIST_NOT_SAVE);
        validateOrderById(ordersRequestDto.getId(), MessageResource.ORDER_NOT_EXIST_NOT_UPDATE);
        dateValidator(ordersRequestDto.getDateCreate().toString(), MessageResource.DATA_USER_CREATE_NOT_VALID_NOT_UPDATE);
        return ordersMapper.toResponseDto(ordersRepository.save(ordersMapper.toModel(ordersRequestDto)));
    }

    @Override
    public List<OrderResponseDto> findByAll(){
        List<Orders> list = ordersRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(ordersMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NOT_EXIST_RECORD);
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Optional<Orders> optionOrdder = ordersRepository.findById(id);
        if(optionOrdder.isPresent()){
            return optionOrdder.map(ordersMapper::toResponseDto).get();
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NOT_EXIST_RECORD);
    }

    @Override
    public List<OrderResponseDto> findByAllOrderNumber(Long orderNumber) {
        List<Orders> listOrdder = ordersRepository.findByOrderNumber(orderNumber);
        if(!listOrdder.isEmpty()){
            return listOrdder.stream().map(ordersMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD);
    }

    @Override
    public void ExcelOrders(OutputStream outputStream, Long orderNumber) throws IOException {
        List<Orders> listOrdder = ordersRepository.findByOrderNumber(orderNumber);

        if(listOrdder.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD);
        }
        try(Workbook workbookExcel = new SXSSFWorkbook()){
            Sheet sheet = workbookExcel.createSheet("Fixed");
            CellStyle cellStyle = workbookExcel.createCellStyle();

            cellStyle.setBorderTop(BorderStyle.MEDIUM);
            cellStyle.setBorderRight(BorderStyle.MEDIUM);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            cellStyle.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);

            // Header
            Row row = sheet.createRow(0);
            Cell cell;
            for(int i=0;i<encabezados.length;i++){
                cell = armaFilas(row.createCell(i),cellStyle, encabezados[i]);
            }

            int idx = 1;
            for(Orders list : listOrdder){
                row = sheet.createRow(idx);
                ObjectMapper oMapper = new ObjectMapper();

                Map<String, Object> map = oMapper.convertValue(list, Map.class);

                List<Object> values =  Arrays.asList(map.values().toArray());

                for(int i=0;i<values.size();i++){
                    cell = armaFilas(row.createCell(i),cellStyle, values.get(i).toString());
                }
                idx++;
            }

          /*int idx = 0;
            for(Orders list : listOrdder){
                row = sheet.createRow(idx);

                cell = row.createCell(0);
                cell.setCellValue(idx);
                cell.setCellStyle(cellStyle);

                cell = row.createCell(1);
                cell.setCellValue(list.getModel());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(2);
                cell.setCellValue(list.getSerialNumber());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(3);
                cell.setCellValue(list.getAsset());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(4);
                cell.setCellValue(list.getIssue());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(5);
                cell.setCellValue(list.getIncident());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(6);
                cell.setCellValue(list.getNote());
                cell.setCellStyle(cellStyle);

                cell = row.createCell(7);
                cell.setCellValue(list.getStatusOrder());
                cell.setCellStyle(cellStyle);

                idx++;
            } */
            workbookExcel.write(outputStream);
            workbookExcel.close();
            outputStream.close();
        }catch (IOException e){
            throw new OrdersExceptionBadRequest(e.getMessage());
        }
    }

    private void validateUsersById(Long id, String message) {
        usersRepository.findById(id).orElseThrow(() -> new UsersExceptionBadRequest(message));
    }

    private void validateOrderById(Long id, String message) {
        ordersRepository.findById(id).orElseThrow(() -> new OrdersExceptionBadRequest(message));
    }

    public void dateValidator(String date, String message)
    {
        DateValidator validator = DateValidator.getInstance();
        if(!validator.isValid(date,"YYYY-MM-dd", Locale.ENGLISH)){
            throw new OrdersExceptionBadRequest(message);
        }
    }

    public Cell armaFilas(Cell cell, CellStyle cellStyle, String description){;
        cell.setCellValue(description);
        cell.setCellStyle(cellStyle);
        return cell;
    }

}
