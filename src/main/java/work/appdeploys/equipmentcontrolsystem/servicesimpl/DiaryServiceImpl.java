package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.DiaryExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.DiaryMapper;
import work.appdeploys.equipmentcontrolsystem.models.Diary;
import work.appdeploys.equipmentcontrolsystem.models.School;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.models.dtos.DiaryDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.DiaryResponse;
import work.appdeploys.equipmentcontrolsystem.repositories.DiaryRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.SchoolRepository;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.DiaryService;

import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryMapper diaryMapper;

    private final DiaryRepository diaryRepository;

    private final SchoolRepository schoolRepository;

    private final UsersRepository usersRepository;

    @Override
    public DiaryResponse save(DiaryDto diaryDto) {
        validateTime(diaryDto.getStartTime(),diaryDto.getEndingTime());
        Diary diary;
        try{
            diary = diaryMapper.toModel(diaryDto);
            diary.setSchool(validateSchoolById(diaryDto.getSchoolDto().getId(),MessageResource.SCHOOL_EXIST_NOT_SAVE));
            diary.setUser(validateUsersById(diaryDto.getUserResponseDto().getId(), MessageResource.USER_CREATE_ORDER_NOT_EXIST_NOT_SAVE));
            diary.setId(null);
            diaryRepository.save(diary);
        }catch (Exception ex){
            throw new DiaryExceptionBadRequest(ex.getMessage());
        }

        return diaryMapper.toResponseDto(diary);
    }

    private Users validateUsersById(Long id, String message) {
        return usersRepository.findById(id).orElseThrow(() -> new DiaryExceptionBadRequest(message));
    }

    private School validateSchoolById(Long id, String message){
        return schoolRepository.findById(id).orElseThrow(() -> new DiaryExceptionBadRequest(message));
    }

    private void validateTime(LocalTime startTime,LocalTime endTime){
        if (startTime.isAfter(endTime)){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_TIME_START_INVALID_NOT_SAVE);
        }
        if(endTime.isBefore(startTime)){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_TIME_ENDING_INVALID_NOT_SAVE);
        }
    }

}
