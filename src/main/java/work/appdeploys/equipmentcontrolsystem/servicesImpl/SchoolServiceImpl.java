package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.SchoolExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.SchoolMapper;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.services.SchoolService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    @Override
    public SchoolDto save(SchoolDto schoolDto) {
        validateSchoolByName(schoolDto, MessageResource.SCHOOL_EXIST_NAME_NOT_UPDATE);
        School entity = schoolMapper.toModel(schoolDto);
        entity.setId(null);
        return schoolMapper.toDto(schoolRepository.save(entity));
    }

    @Override

    public void delete(Long id) {
        validateSchoolById(id, MessageResource.SCHOOL_NOT_EXIST_NOT_DELETE);
        schoolRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public SchoolDto update(SchoolDto schoolDto) {
        validateSchoolById(schoolDto.getId(), MessageResource.SCHOOL_NOT_EXIST_NOT_UPDATE);
        validateSchoolByName(schoolDto, MessageResource.SCHOOL_EXIST_NAME_NOT_UPDATE);
        return schoolMapper.toDto(schoolRepository.save(schoolMapper.toModel(schoolDto)));
    }

    @Override
    public List<SchoolDto> findByAll() {
        List<School> list =  schoolRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(schoolMapper::toDto).collect(Collectors.toList());
        }
        throw new SchoolExceptionBadRequest(MessageResource.SCHOOLS_NOT_EXIST_RECORDS);
    }

    @Override
    public SchoolDto findById(Long id) {
        Optional<School> optionSchool =  schoolRepository.findById(id);
        if(optionSchool.isPresent()){
            return optionSchool.map(schoolMapper::toDto).get();
        }
        throw new SchoolExceptionBadRequest(MessageResource.SCHOOLS_NOT_EXIST_RECORDS);
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