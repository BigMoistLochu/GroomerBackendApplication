package aplikacja.groomerbackend.services.validators;


import aplikacja.groomerbackend.dto.AuthRequestDto;

public class AuthRequestDtoValidator {


    /**
     * If email and password from AuthRequestDto is correct then return true, otherwise false
     * @param authRequestDto
     * @return
     */
    public boolean validateAuthRequest(AuthRequestDto authRequestDto){

        return authRequestDto.getEmail() != null
                && authRequestDto.getPassword() != null
                && !authRequestDto.getEmail().isBlank()
                && !authRequestDto.getPassword().isBlank()
                && authRequestDto.getEmail().contains("@")
                && authRequestDto.getEmail().length() <= 50
                && authRequestDto.getPassword().length() >= 10
                && authRequestDto.getPassword().length() <= 30;
    }




}
