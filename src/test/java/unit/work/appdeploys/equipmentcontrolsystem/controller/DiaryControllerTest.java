package unit.work.appdeploys.equipmentcontrolsystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import work.appdeploys.equipmentcontrolsystem.controllers.DiaryController;
import work.appdeploys.equipmentcontrolsystem.services.DiaryService;

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
