package work.appdeploys.equipmentcontrolsystem.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.UsersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.services.UsersService;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;
    @Value("${REGX_EMAIL}")
    private String regxEmail;
    @Value("${REGX_PWD}")
    private String regxPwd;

    @Override
    public UsersDto save(UsersDto usersDto){
        validateFields(usersDto," ");
        if(usersRepository.findById(usersDto.getId()).isPresent()){
            throw new UsersExceptionBadRequest(MessageResource.USERS_EXIST_NOT_SAVE);
        }
        validateUsersByEmail(usersDto,MessageResource.USERS_EMAIL_ALREADY_EXIST_NOT_SAVE);
        return usersMapper.toDto(usersRepository.save(usersMapper.toModel(usersDto)));
    }

    @Override
    public void delete(Long id) {
        validateUsersById(id,MessageResource.USERS_EXIST_NOT_DELETE);
        usersRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public UsersDto update(UsersDto usersDto) {
        validateFields(usersDto, MessageResource.UPDATE_FAIL);
        validateUsersById(usersDto.getId(),MessageResource.USERS_NOT_EXIST_NOT_UPDATE);
        validateUsersByEmail(usersDto,MessageResource.USERS_EMAIL_ALREADY_EXIST_NOT_UPDATE);
        return usersMapper.toDto(usersRepository.save(usersMapper.toModel(usersDto)));
    }

    public List<UsersDto> findByAll(){
        List<Users> list = usersRepository.findAll();
        if(!list.isEmpty()){
            return list.stream().map(usersMapper::toDto).collect(Collectors.toList());
        }
        throw new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS);
    }

    private void validateUsersById(Long id, String message) {
        usersRepository.findById(id).orElseThrow(() -> new UsersExceptionBadRequest(message));
    }

    private void validateUsersByEmail(UsersDto usersDto, String message) {
        var users = usersRepository.findByEmail(usersDto.getEmail());
        if (!users.isEmpty() && users.get().getId() != usersDto.getId()) {
            throw new UsersExceptionBadRequest(message);
        }
    }

    public boolean validteRegx(String validateTxt, String regx){
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(validateTxt);
        return matcher.matches();
    }

    public void validateFields(UsersDto usersDto, String message){
        if(!validteRegx(usersDto.getEmail(), regxEmail)){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_EMAIL + message);
        }
        if(!validteRegx(usersDto.getPasswd(), regxPwd)){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_PASSWORD + message);
        }
        if(!usersDto.getRol().equals("1") && !usersDto.getRol().equals("2")){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_ROL + message);
        }
    }


}
