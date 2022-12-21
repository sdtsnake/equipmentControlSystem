package unit.work.appdeploys.equipmentcontrolsystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import work.appdeploys.equipmentcontrolsystem.controllers.DiaryController;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;
import work.appdeploys.equipmentcontrolsystem.services.DiaryService;

import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class DiaryControllerTest {
    @Mock
    DiaryService diaryService;

    @InjectMocks
    DiaryController diaryController;

    //se inicializa siempre los insumos.
    @BeforeEach
    public void beforeEach(){

    }

    @Test
    public void saveTest(){

    }





}
