package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.DiaryExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.OrdersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
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
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryMapper diaryMapper;

    private final DiaryRepository diaryRepository;

    private final SchoolRepository schoolRepository;

    private final UsersRepository usersRepository;

    @Override
    public DiaryResponse save(DiaryDto diaryDto) {
        if(diaryRepository.findById(diaryDto.getId()).isPresent()){
            throw new UsersExceptionBadRequest(MessageResource.DIARY_EXIST_NOT_SAVE);
        }

        validateDiaryFields(diaryDto);
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

    @Override
    public void delete(long id) {
        validateDiary(id,MessageResource.DIARY_NOT_EXIST_NOT_DELETE);
        diaryRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public DiaryResponse update(DiaryDto diaryDto) {
        validateDiaryFields(diaryDto);
        validateDiary(diaryDto.getId(),MessageResource.DIARY_NOT_EXIST_NOT_UPDATE);
        return diaryMapper.toResponseDto(diaryRepository.save(diaryMapper.toModel(diaryDto)));
    }

    @Override
    public List<DiaryResponse> findByAll() {
        List<Diary> list = diaryRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(diaryMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new OrdersExceptionBadRequest(MessageResource.DIARY_NOT_EXIST_RECORD);
    }

    private Users validateUsersById(Long id, String message) {
        return usersRepository.findById(id).orElseThrow(() -> new DiaryExceptionBadRequest(message));
    }

    private School validateSchoolById(Long id, String message){
        return schoolRepository.findById(id).orElseThrow(() -> new DiaryExceptionBadRequest(message));
    }

    private boolean validaWeekday(int weekday) {
        Calendar c = Calendar.getInstance(Locale.US);
        if (c.SUNDAY == weekday)
            return true;
        if (c.WEDNESDAY == weekday)
            return true;
        if (c.TUESDAY == weekday)
            return true;
        if (c.THURSDAY == weekday)
            return true;
        if (c.FRIDAY == weekday)
            return true;
        if (c.SUNDAY == weekday)
            return true;
        return false;
    }

    private void validateTime(LocalTime startTime,LocalTime endTime){
        if (startTime.isAfter(endTime)){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_TIME_START_INVALID_NOT_SAVE);
        }
        if(endTime.isBefore(startTime)){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_TIME_ENDING_INVALID_NOT_SAVE);
        }
    }

    private void validateDiary(long id, String message){
        diaryRepository.findById(id).orElseThrow(()-> new DiaryExceptionBadRequest(message));
    }

    private void validateDiaryFields(DiaryDto diaryDto){
        validateTime(diaryDto.getStartTime(),diaryDto.getEndingTime());
        if(!validaWeekday(diaryDto.getWeekday())){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_WEEKDAY_INVALID_NOT_SAVE);
        }

        if(diaryDto.getReplacement().toUpperCase() != "X" || !diaryDto.getReplacement().isEmpty()){
            throw new DiaryExceptionBadRequest(MessageResource.DIARY_REPLACEMENT_INVALID_NOT_SAVE);
        }
    }

}
