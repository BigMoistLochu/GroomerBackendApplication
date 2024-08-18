package aplikacja.groomerbackend.exceptions;

public class UserNotSavedIntoDbException extends RuntimeException{

    public UserNotSavedIntoDbException(String message){
        super(message);
    }

}
