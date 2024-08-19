package aplikacja.groomerbackend.controllers.exceptionsHandler;


import aplikacja.groomerbackend.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, UsernameNotFoundException.class, UserAlreadyExistsException.class})
    public ResponseEntity<CustomExceptionResponse> handleBadRequestAndUserExceptions(Exception exception){
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomExceptionResponse> handleDatabaseOrServerIssueExceptions(RuntimeException exception){
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }




}
