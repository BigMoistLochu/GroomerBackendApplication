package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.entity.UserEntity;
import aplikacja.groomerbackend.repository.UserRepository;
import aplikacja.groomerbackend.services.validators.AuthRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final AuthRequestDtoValidator requestValidator = new AuthRequestDtoValidator();

    public AuthenticationService(JwtService jwtService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public String loginUser(AuthRequestDto request){

        if(!requestValidator.validateAuthRequest(request)){
            throw new IllegalArgumentException("Bad credentials");
        }

        UserEntity userFromDB = userRepository.findByEmail(request.getEmail());

        if(userFromDB==null){
            throw new UsernameNotFoundException("User with this email not found");
        }

        return jwtService.generateToken(userFromDB);
    }


    public boolean registerUser(AuthRequestDto request){

        //validacja requesta
        //sprawdzenie go w bazie czy nie istnieje juz
        //rejestra usera
        //ologowanie tego

        return true;
    }



}
