package aplikacja.groomerbackend.services.validators;


import aplikacja.groomerbackend.dto.AuthCredentialsRequestDto;

public class AuthRequestDtoValidator {


    private final int PASSWORD_MIN_LENGTH = 10;
    private final int PASSWORD_MAX_LENGTH = 30;

    private final int EMAIL_MIN_LENGTH = 5;
    private final int EMAIL_MAX_LENGTH = 50;

    private final int USERNAME_MIN_LENGTH = 5;
    private final int USERNAME_MAX_LENGTH = 40;

    /**
     * Validates email and password for login purposes.
     * @param authCredentialsRequestDto
     * @return true if valid, otherwise false
     */
    public boolean validateLoginRequest(AuthCredentialsRequestDto authCredentialsRequestDto){
        return isEmailValid(authCredentialsRequestDto.getEmail()) && isPasswordValid(authCredentialsRequestDto.getPassword());
    }

    /**
     * Validates email, password, and username for registration purposes.
     * @param authCredentialsRequestDto
     * @return true if valid, otherwise false
     */
    public boolean validateRegistrationRequest(AuthCredentialsRequestDto authCredentialsRequestDto) {
        return isEmailValid(authCredentialsRequestDto.getEmail()) &&
                isPasswordValid(authCredentialsRequestDto.getPassword()) &&
                isUsernameValid(authCredentialsRequestDto.getUsername());
    }


    boolean isEmailValid(String email){
        if(email==null) return false;
        if(email.isBlank()) return false;
        if(!email.contains("@")) return false;
        if(!(email.length() >= EMAIL_MIN_LENGTH && email.length() <= EMAIL_MAX_LENGTH)) return false;
        return true;
    }

    boolean isPasswordValid(String password){
        if(password==null) return false;
        if(password.isBlank()) return false;
        if(!(password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH)) return false;
        return true;
    }

    boolean isUsernameValid(String username){
        if(username==null) return false;
        if(!(username.length() >= USERNAME_MIN_LENGTH && username.length() <= USERNAME_MAX_LENGTH)) return false;
        return true;
    }



}
