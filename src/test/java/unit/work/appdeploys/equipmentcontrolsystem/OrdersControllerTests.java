package unit.work.appdeploys.equipmentcontrolsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import work.appdeploys.equipmentcontrolsystem.controllers.OrdersControllers;
import work.appdeploys.equipmentcontrolsystem.models.structures.ExcelDto;
import work.appdeploys.equipmentcontrolsystem.services.FileCopy;
import work.appdeploys.equipmentcontrolsystem.services.OrdersService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersControllerTests {
    @Mock
    OrdersService ordersService;
    @Mock
    FileCopy fileCopy;
    @InjectMocks
    OrdersControllers ordersControllers;
    @BeforeEach
    public void beforeEach(){

    }

    @Test
    public void generarExcelTest() throws IOException {
        //precondiciones
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        LocalDate localDate = LocalDate.now();
        Long idschool = 1L;
        ExcelDto excelDto = new ExcelDto("excel",new File("/excel"));
        String headerVal = "attachment; filename=" + excelDto.getNameExcel();
        when(ordersService.excelOrders(localDate,idschool)).thenReturn(excelDto);
        when(response.getOutputStream()).thenReturn(Mockito.mock(ServletOutputStream.class));
        //ejecucion
        ordersControllers.ordersExceclRespor(response,localDate,idschool);

        //validacion
        verify(ordersService).excelOrders(localDate,idschool);
        verify(response).setHeader("Content-Disposition", headerVal);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void generarExcelBorrarTmpTest() throws IOException {
        //precondiciones
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        File file = Mockito.mock(File.class);
        LocalDate localDate = LocalDate.now();
        Long idschool = 1L;
        ExcelDto excelDto = new ExcelDto("excel",file);
        String headerVal = "attachment; filename=" + excelDto.getNameExcel();
        when(ordersService.excelOrders(localDate,idschool)).thenThrow(new IOException());

        //ejecucion
        ordersControllers.ordersExceclRespor(response,localDate,idschool);

        //validacion
        verify(file,times(0)).delete();
    }

    @Test
    public void generarExcelTestBorrarOk() throws IOException {
        //precondiciones
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        LocalDate localDate = LocalDate.now();
        Long idschool = 1L;
        File file = Mockito.mock(File.class);
        ExcelDto excelDto = new ExcelDto("excel",file);
        String headerVal = "attachment; filename=" + excelDto.getNameExcel();
        when(ordersService.excelOrders(localDate,idschool)).thenReturn(excelDto);

        //ejecucion
        ordersControllers.ordersExceclRespor(response,localDate,idschool);

        //validacion
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(file).delete();
    }

    @Test
    public void generarExcelTestBorrarException() throws IOException {
        //precondiciones
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        LocalDate localDate = LocalDate.now();
        Long idschool = 1L;
        File file = Mockito.mock(File.class);
        ExcelDto excelDto = new ExcelDto("excel",file);
        String headerVal = "attachment; filename=" + excelDto.getNameExcel();
        when(ordersService.excelOrders(localDate,idschool)).thenReturn(excelDto);
        doThrow(new IOException()).when(fileCopy).copyFile(any(),any());

        //ejecucion
        ordersControllers.ordersExceclRespor(response,localDate,idschool);

        //validacion
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        verify(file).delete();
    }


}
