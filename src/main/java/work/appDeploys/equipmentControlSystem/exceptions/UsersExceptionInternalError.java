package work.appDeploys.equipmentControlSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UsersExceptionInternalError extends RuntimeException{
    public UsersExceptionInternalError(String message) {
        super(message);
    }
}
