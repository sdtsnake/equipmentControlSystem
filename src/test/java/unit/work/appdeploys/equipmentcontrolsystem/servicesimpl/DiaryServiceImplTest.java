package unit.work.appdeploys.equipmentcontrolsystem.servicesimpl;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import work.appdeploys.equipmentcontrolsystem.mappers.DiaryMapper;
import work.appdeploys.equipmentcontrolsystem.models.Diary;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.UsersResponse;
import work.appdeploys.equipmentcontrolsystem.repositories.DiaryRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.DiaryService;
import work.appdeploys.equipmentcontrolsystem.servicesimpl.DiaryServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DiaryServiceImplTest {

    @Mock
    DiaryRepository diaryRepository;

    @Mock
    SchoolRepository schoolRepository;

    @Mock
    UsersRepository usersRepository;

    @Mock
    DiaryMapper diaryMapper;

    @InjectMocks
    DiaryServiceImpl diaryService;

    @Test
    void saveSuccessfulTest() {
        //precondiciones
/*        School school = new School();
        UserResponseDto userResponseDto = new UserResponseDto();
        Users users = new Users();
        Diary diary = new Diary();
        DiaryDto diaryDto = DiaryDto.builder()
                .id(1L)
                .userResponseDto(userResponseDto)
                .schoolDto()
                .build() ;
        when(diaryRepository.findById(any())).thenReturn(Optional.empty());
        when(schoolRepository.findById(any())).thenReturn(Optional.of(school));
        when(usersRepository.findById(any())).thenReturn(Optional.of(users));
        when(diaryMapper.toModel(any())).thenReturn(diary);
        //ejecucion
        diaryService.save();*/



    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findByAll() {
    }

    @Test
    void findById() {
    }
}