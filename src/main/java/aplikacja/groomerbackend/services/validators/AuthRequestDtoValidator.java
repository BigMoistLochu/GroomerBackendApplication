package aplikacja.groomerbackend.services.validators;


import aplikacja.groomerbackend.dto.AuthRequestDto;

public class AuthRequestDtoValidator {


    /**
     * Validates email and password for login purposes.
     * @param authRequestDto
     * @return true if valid, otherwise false
     */
    public boolean validateLoginRequest(AuthRequestDto authRequestDto){

        return authRequestDto.getEmail() != null
                && authRequestDto.getPassword() != null
                && !authRequestDto.getEmail().isBlank()
                && !authRequestDto.getPassword().isBlank()
                && authRequestDto.getEmail().contains("@")
                && authRequestDto.getEmail().length() <= 50
                && authRequestDto.getPassword().length() >= 10
                && authRequestDto.getPassword().length() <= 30;
    }

    /**
     * Validates email, password, and username for registration purposes.
     * @param authRequestDto
     * @return true if valid, otherwise false
     */
    public boolean validateRegistrationRequest(AuthRequestDto authRequestDto) {
        return authRequestDto.getEmail() != null
                && authRequestDto.getPassword() != null
                && authRequestDto.getUsername() != null
                && !authRequestDto.getEmail().isBlank()
                && !authRequestDto.getPassword().isBlank()
                && !authRequestDto.getUsername().isBlank()
                && authRequestDto.getEmail().contains("@")
                && authRequestDto.getEmail().length() <= 50
                && authRequestDto.getPassword().length() >= 10
                && authRequestDto.getPassword().length() <= 30
                && authRequestDto.getUsername().length() >= 3
                && authRequestDto.getUsername().length() <= 20;
    }




}
