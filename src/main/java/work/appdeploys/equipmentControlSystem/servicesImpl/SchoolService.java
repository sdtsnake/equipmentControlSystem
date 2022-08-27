package work.appdeploys.equipmentControlSystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentControlSystem.excetions.SchoolExceptionBadRequest;
import work.appdeploys.equipmentControlSystem.mappers.SchoolMapper;
import work.appdeploys.equipmentControlSystem.models.dtos.SchoolDto;
import work.appdeploys.equipmentControlSystem.repositories.SchoolRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SchoolService implements work.appdeploys.equipmentControlSystem.services.SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolDto save(SchoolDto schoolDto) {
        var school = schoolRepository.findByName(schoolDto.getName());
        if(school.isPresent()){
            throw new SchoolExceptionBadRequest("The School already exist, cant be save");
        }
        return schoolMapper.toDto(schoolRepository.save(schoolMapper.toModel(schoolDto)));
    }

    public void delete(Long id) {
        var school = schoolRepository.findById(id);
        if(school.isEmpty()){
            throw new SchoolExceptionBadRequest("The School no exist, cant be delete");
        }
        schoolRepository.deleteAllById(Collections.singleton(id));
    }

    public SchoolDto update(SchoolDto schoolDto) {
        var school = schoolRepository.findById(schoolDto.getId());
        if(school.isEmpty()){
            throw new SchoolExceptionBadRequest("The School no exist, cant be update");
        }
        school = schoolRepository.findByName(schoolDto.getName());
        if(school.isPresent()){
            throw new SchoolExceptionBadRequest("The School name already exist, cant be update");
        }
        return schoolMapper.toDto(schoolRepository.save(schoolMapper.toModel(schoolDto)));
    }
}
