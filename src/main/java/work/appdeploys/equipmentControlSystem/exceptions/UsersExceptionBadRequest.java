package work.appdeploys.equipmentControlSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsersExceptionBadRequest extends RuntimeException {
    public UsersExceptionBadRequest(String message) {
        super(message);
    }
}
