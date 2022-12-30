package work.appdeploys.equipmentcontrolsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StatusOrderBySchoolBadRequest extends RuntimeException {

    public StatusOrderBySchoolBadRequest(String message) {
        super(message);
    }
}
