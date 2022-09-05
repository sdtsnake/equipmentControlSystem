package work.appdeploys.equipmentControlSystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentControlSystem.constants.MessageResource;
import work.appdeploys.equipmentControlSystem.exceptions.SchoolExceptionBadRequest;
import work.appdeploys.equipmentControlSystem.mappers.SchoolMapper;
import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentControlSystem.repositories.SchoolRepository;
import work.appdeploys.equipmentControlSystem.services.SchoolService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolDto save(SchoolDto schoolDto) {
        validateSchoolByName(schoolDto,MessageResource.SCHOOL_EXIST_NOT_SAVE);
        return schoolMapper.toDto(schoolRepository.save(schoolMapper.toModel(schoolDto)));
    }

    public void delete(Long id) {
        validateSchoolById(id, MessageResource.SCHOOL_NOT_EXIST_NOT_DELETE);
        schoolRepository.deleteAllById(Collections.singleton(id));
    }

    public SchoolDto update(SchoolDto schoolDto) {
        validateSchoolById(schoolDto.getId(), MessageResource.SCHOOL_NOT_EXIST_NOT_UPDATE);
        validateSchoolByName(schoolDto, MessageResource.SCHOOL_EXIST_NAME_NOT_UPDATE);
        return schoolMapper.toDto(schoolRepository.save(schoolMapper.toModel(schoolDto)));
    }

    private void validateSchoolById(Long schoolDto, String message) {
        var school = schoolRepository.findById(schoolDto);
        if (school.isEmpty()) {
            throw new SchoolExceptionBadRequest(message);
        }
    }

    private void validateSchoolByName(SchoolDto schoolDto, String message) {
        var school = schoolRepository.findByName(schoolDto.getName());
        if (school.isPresent()) {
            throw new SchoolExceptionBadRequest(message);
        }
    }
}