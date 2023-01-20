package work.appdeploys.equipmentcontrolsystem.servicesimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.appdeploys.equipmentcontrolsystem.constants.MessageResource;
import work.appdeploys.equipmentcontrolsystem.exceptions.UsersExceptionBadRequest;
import work.appdeploys.equipmentcontrolsystem.mappers.UsersMapper;
import work.appdeploys.equipmentcontrolsystem.models.Users;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UserResponseDto;
import work.appdeploys.equipmentcontrolsystem.models.dtos.UsersDto;
import work.appdeploys.equipmentcontrolsystem.models.structures.UsersResponseDto;
import work.appdeploys.equipmentcontrolsystem.repositories.UsersRepository;
import work.appdeploys.equipmentcontrolsystem.security.UserDetailsImpl;
import work.appdeploys.equipmentcontrolsystem.services.UsersService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public UserResponseDto save(UsersDto usersDto){
        validateFields(usersDto," ");
        if(usersRepository.findById(usersDto.getId()).isPresent()){
            throw new UsersExceptionBadRequest(MessageResource.USERS_EXIST_NOT_SAVE.getValue());
        }
        validateUsersByEmail(usersDto,MessageResource.USERS_EMAIL_ALREADY_EXIST_NOT_SAVE.getValue());
        String passwd = new BCryptPasswordEncoder().encode(usersDto.getPasswd());
        usersDto.setPasswd(passwd);
        return usersMapper.toResponseDto(usersRepository.save(usersMapper.toModel(usersDto)));
    }

    @Override
    public void delete(Long id) {
        validateUsersById(id,MessageResource.USERS_EXIST_NOT_DELETE.getValue());
        usersRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public UserResponseDto update(UsersDto usersDto) {
        validateFields(usersDto, MessageResource.UPDATE_FAIL.getValue());
        validateUsersById(usersDto.getId(),MessageResource.USERS_NOT_EXIST_NOT_UPDATE.getValue());
        validateUsersByEmail(usersDto,MessageResource.USERS_EMAIL_ALREADY_EXIST_NOT_UPDATE.getValue());
        String passwd = new BCryptPasswordEncoder().encode(usersDto.getPasswd());
        usersDto.setPasswd(passwd);
        return usersMapper.toResponseDto(usersRepository.save(usersMapper.toModel(usersDto)));
    }

    @Override
    public List<UserResponseDto> findByAll(){
        List<Users> usersRepositoryAll = usersRepository.findAll();
        if(!usersRepositoryAll.isEmpty()){
            return usersRepositoryAll.stream().map(usersMapper::toResponseDto).collect(Collectors.toList());
        }
        throw new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS.getValue());
    }

    @Override
    public UsersDto findById(Long id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.map(usersMapper::toResponseUserDto).get();
        }
        throw new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS.getValue());
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);
        if(userOptional.isPresent()){
            return userOptional.map(usersMapper::toResponseDto).get();
        }
        throw new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS_EMAIL.getValue());
    }

    @Override
    public UserDetails findOneByEmail(String email) {
        Users users = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsersExceptionBadRequest(MessageResource.USERS_NOT_EXIST_RECORDS_EMAIL.getValue()));

        return new UserDetailsImpl(users);
    }

    private void validateUsersById(Long id, String message) {
        usersRepository.findById(id).orElseThrow(() -> new UsersExceptionBadRequest(message));
    }

    private void validateUsersByEmail(UsersDto usersDto, String message) {
        var users = usersRepository.findByEmail(usersDto.getEmail());
        if (users.isPresent() && !users.get().getId().equals(usersDto.getId())) {
            throw new UsersExceptionBadRequest(message);
        }
    }

    public boolean validteRegx(String validateTxt, String regx){
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(validateTxt);
        return !matcher.matches();
    }

    public void validateFields(UsersDto usersDto, String message){
        if(validteRegx(usersDto.getEmail(), regxEmail)){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_EMAIL + message);
        }
        if(validteRegx(usersDto.getPasswd(), regxPwd)){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_PASSWORD + message);
        }
        if(!usersDto.getRol().equals("1") && !usersDto.getRol().equals("2")){
            throw new UsersExceptionBadRequest(MessageResource.USER_BAT_ROL + message);
        }
    }

}
