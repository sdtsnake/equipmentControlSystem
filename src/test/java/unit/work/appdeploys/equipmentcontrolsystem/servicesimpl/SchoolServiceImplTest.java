package unit.work.appdeploys.equipmentcontrolsystem.servicesimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import work.appdeploys.equipmentcontrolsystem.exceptions.SchoolExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.SchoolMapper;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.servicesimpl.SchoolServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolServiceImplTest {

    @Mock
    SchoolMapper schoolMapper;

    @Mock
    SchoolRepository schoolRepository;

    @InjectMocks
    SchoolServiceImpl schoolServiceImpl;

    @Test
    void save() {
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
    void findByIdSchoolRegNotFound() {
        //Precondicion
        when(schoolRepository.findById(any())).thenReturn(Optional.empty());
        //Ejecucion

        SchoolExceptionBadRequest exception = Assertions.assertThrows(SchoolExceptionBadRequest.class, () -> {
            schoolServiceImpl.findById(1L);
        });
        //validaciones
        Assertions.assertEquals("Not exist records of schools", exception.getMessage());
        verify(schoolRepository,times(1)).findById(1L);
        verify(schoolMapper,times(0)).toDto(any());
    }

    @Test
    void findByIdSchoolRegFound() {
        //Precondicion
        School school = new School();

        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(1L);
        schoolDto.setName("Lancaster");
        when(schoolRepository.findById(1L)).thenReturn(Optional.of(school));
        when(schoolMapper.toDto(school)).thenReturn(schoolDto);
        //Ejecucion
        SchoolDto schoolDto1 = schoolServiceImpl.findById(1L);
        //validaciones
        assertEquals(schoolDto,schoolDto1);
        assertEquals(schoolDto.getId(),schoolDto1.getId());
        assertEquals(schoolDto.getName(),schoolDto1.getName());
        assertEquals("Lancaster",schoolDto1.getName());
        verify(schoolRepository).findById(1L);
        verify(schoolMapper).toDto(school);
    }

}