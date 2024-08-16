package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import aplikacja.groomerbackend.exceptions.UserAlreadyExistsException;
import aplikacja.groomerbackend.repository.UserRepository;
import aplikacja.groomerbackend.services.validators.AuthRequestDtoValidator;
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

    public String validateUserAndGenerateToken(AuthRequestDto request){

        if(!requestValidator.validateLoginRequest(request)){
            throw new IllegalArgumentException("Bad credentials");
        }

        UserEntity userFromDB = userRepository.findByEmail(request.getEmail());

        if(userFromDB==null){
            throw new UsernameNotFoundException("User with this email not found");
        }

        return jwtService.generateToken(userFromDB);
    }


    public void validateAndCreateUser(AuthRequestDto request){

        if(!requestValidator.validateRegistrationRequest(request)){
            throw new IllegalArgumentException("Bad credentials");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("User with that email already Exists");
        }

        UserEntity user = new UserEntity(request.getUsername(),request.getEmail(),request.getPassword(),null, Role.EMPLOYEE,false);

        userRepository.save(user);

        //loggowanie tego
        //zwroc obiekt??
    }



}
