package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.SchoolExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.SchoolMapper;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.services.SchoolService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolDto save(SchoolDto schoolDto) {
        validateSchoolByName(schoolDto, MessageResource.SCHOOL_EXIST_NAME_NOT_UPDATE);
        if(schoolRepository.findById(schoolDto.getId()).isPresent()){
            throw new UsersExceptionBadRequest(MessageResource.SCHOOL_EXIST_NOT_SAVE);
        }
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

    public List<SchoolDto> findByAll() {
        List<School> list =  schoolRepository.findAll();
        List<SchoolDto> listDto = new ArrayList<>();

        if(!list.isEmpty()){
            /*metodo 1
            for (School sch1:list){
                listDto.add(schoolMapper.toDto(sch1));
            }*/

            // metodo 3
            return list.stream().map(schoolMapper::toDto).collect(Collectors.toList());
        }

        throw new SchoolExceptionBadRequest(MessageResource.SCHOOL_NOT_EXIST_RECORD);

        //metodo 2
        //list.stream().map(school ->schoolMapper.toDto(school)).collect(Collectors.toList());



    }

    private void validateSchoolById(Long schoolDto, String message) {
        var school = schoolRepository.findById(schoolDto);
        if (school.isEmpty()) {
            throw new SchoolExceptionBadRequest(message);
        }
    }

    private void validateSchoolByName(SchoolDto schoolDto, String message) {
        var school = schoolRepository.findByName(schoolDto.getName());
        if (school.isPresent() && school.get().getId() != schoolDto.getId()) {
            throw new SchoolExceptionBadRequest(message);
        }
    }
}