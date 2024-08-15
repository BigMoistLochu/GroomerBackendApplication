package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.services.validators.AuthRequestDtoValidator;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRequestDtoValidator requestValidator = new AuthRequestDtoValidator();

    public boolean loginUser(AuthRequestDto request){

        if(!requestValidator.validateAuthRequest(request)){
            //jesli dane sa nie okey to puscisz exceptiona ktorego zlapiesz na poziomie kontrollera
        }



        return true;
    }




}
