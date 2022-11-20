package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.OrdersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Orders;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrderResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.OrdersRequestDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.ExcelDto;
import work.appdeploys.equipmentcontrolsystem.repositories.OrdersRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private Function<SXSSFWorkbook, XSSFCellStyle> getSchoolStyle = workbook-> crearEstiloCelda(workbook,IndexedColors.LIGHT_ORANGE,false);
    private Function<SXSSFWorkbook, XSSFCellStyle> getTitleStyle = workbook-> crearEstiloCelda(workbook,IndexedColors.LIGHT_YELLOW,true);
    private Function<SXSSFWorkbook, XSSFCellStyle> getStyle = workbook-> crearEstiloCelda(workbook,IndexedColors.WHITE,false);
    private final OrdersMapper ordersMapper;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final SchoolRepository schoolRepository;
    private final String [] headers = {"Order","Model Number","Serial Number","Tag Asset","Issue","Incident #","Repair","Status"};
    private final String [] status = {"Fixed","Recycle","waiting"};

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
    public ExcelDto excelOrders(LocalDate dateTo, Long idSchool) throws IOException {
        Optional<School> optSchool = schoolRepository.findById(idSchool);
        if(optSchool.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.SCHOOLS_NOT_EXIST_RECORDS);
        }
        List<Orders> listOrder = ordersRepository.findByDateCreate(dateTo);
        if(listOrder.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD);
        }
        List<Orders> subListSchool = listOrder.stream()
                .filter(order -> order.getSchool().getId().equals(idSchool))
                .collect(Collectors.toList());
        if(subListSchool.isEmpty()){
            throw new OrdersExceptionBadRequest(MessageResource.ORDER_NUNBER_NOT_EXIST_RECORD);
        }

        File excelTmp = createExcel(subListSchool);
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_DD");
        return new ExcelDto(optSchool.get().getName().replaceAll(" ", "_") + "_" + dateFormat.format(new Date()) + ".xlsx",excelTmp);
    }

    public File createExcel(List<Orders> subListSchool) {
        File excelTmp = new File(String.format("tmp%s%s", System.currentTimeMillis(),Thread.currentThread().getId()));
        try{
            FileOutputStream outputStream = new FileOutputStream(excelTmp);

            try(SXSSFWorkbook workbookExcel = new SXSSFWorkbook()){

                for (String tag:status) {
                    List<Orders> subList = subListSchool.stream()
                            .filter(order -> tag.equalsIgnoreCase(order.getStatusOrder().trim()))
                            .collect(Collectors.toList());
                    if(!subList.isEmpty()){
                        generatedSheet(tag,subList,workbookExcel);
                    }
                }
                workbookExcel.write(outputStream);
            }catch (Exception e){
                throw new OrdersExceptionBadRequest(e.getMessage());
            }
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            throw new OrdersExceptionBadRequest(e.getMessage());
        }

        return excelTmp;
    }
    public void generatedSheet(String tag, List<Orders> listOrdder, SXSSFWorkbook workbookExcel){

        XSSFCellStyle cellStyleTitle = getTitleStyle.apply(workbookExcel);
        XSSFCellStyle cellStyleSchool = getSchoolStyle.apply(workbookExcel);
        XSSFCellStyle cellStyle = getStyle.apply(workbookExcel);
        SXSSFSheet sheet = workbookExcel.createSheet(tag);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0, headers.length - 1));

        // Header
        Row row = sheet.createRow(0);
        Cell cell;

        ObjectMapper oMapper = new ObjectMapper();
        oMapper.registerModule(new JavaTimeModule());

        Integer idx = 2;
        Integer fila = 1;
        boolean impTitle = true;
        for(Orders order : listOrdder){

            if(impTitle){
                cell = armaFilas(row.createCell(0),cellStyleSchool, order.getSchool().getName() + " - " + tag);
                CellUtil.setCellStyleProperty(cell, CellUtil.ALIGNMENT, HorizontalAlignment.CENTER);

                row = sheet.createRow(1);
                for(int i = 0; i< headers.length; i++){
                    cell = armaFilas(row.createCell(i),cellStyleTitle, headers[i]);
                }
                impTitle = false;
            }

            row = sheet.createRow(idx);

            Map<String, Object> map = oMapper.convertValue(ordersMapper.toModelExcelDto(order), Map.class);

            List<Object> values =  Arrays.asList(map.values().toArray());

            armaFilas(row.createCell(0),cellStyle, fila.toString());

            for(int i=0;i<values.size();i++){
                cell = armaFilas(row.createCell(i+1),cellStyle, values.get(i).toString());
            }
            fila++;
            idx++;
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

    public Cell armaFilas(Cell cell, CellStyle cellStyle, String description){
        cell.setCellValue(description);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    private XSSFCellStyle crearEstiloCelda(SXSSFWorkbook workbook, IndexedColors color, boolean warpText) {
        Font datosFontWhite = workbook.createFont();
        datosFontWhite.setBold(false);
        datosFontWhite.setFontName("Consolas");
        datosFontWhite.setFontHeightInPoints((short) 10);

        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setWrapText(warpText);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setFillForegroundColor(color.index);
        style.setFont(datosFontWhite);
        return style;
    }
}
