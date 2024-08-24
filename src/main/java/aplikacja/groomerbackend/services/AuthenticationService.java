package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.dto.AuthCredentialsRequestDto;
import aplikacja.groomerbackend.dto.AuthenticateResponseDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import aplikacja.groomerbackend.exceptions.UserAlreadyExistsException;
import aplikacja.groomerbackend.mappers.UserMapper;
import aplikacja.groomerbackend.repository.UserRepository;
import aplikacja.groomerbackend.services.validators.AuthRequestDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final AuthRequestDtoValidator requestValidator = new AuthRequestDtoValidator();

    private final UserMapper userMapper = new UserMapper();

    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(JwtService jwtService, UserRepository userRepository){
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public AuthenticateResponseDto validateAndGenerateBearerToken(AuthCredentialsRequestDto request){

        if(!requestValidator.validateLoginRequest(request)){
            throw new IllegalArgumentException("Bad credentials");
        }

        UserEntity userFromDB = userRepository.findByEmail(request.getEmail());

        if(userFromDB==null){
            throw new UsernameNotFoundException("User with this email not found");
        }

        return jwtService.generateBearerAndRefreshToken(userFromDB);
    }

    public AuthenticateResponseDto regenerateRefreshAndBearerToken(String refreshToken){
        if(!jwtService.validateToken(refreshToken)) throw new IllegalArgumentException("Bad Refresh Token");

        UserEntity user = userMapper.getUserEntityFromClaimsMap(jwtService.getClaimsFromToken(refreshToken));
        return jwtService.generateBearerAndRefreshToken(user);
    }


    public UserEntityDto validateAndRegisterUser(AuthCredentialsRequestDto request){

        if(!requestValidator.validateRegistrationRequest(request)){
            throw new IllegalArgumentException("Bad credentials");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("User with that email already Exists");
        }

        UserEntity user = new UserEntity(request.getUsername(),request.getEmail(),request.getPassword(),null, Role.EMPLOYEE,false);

        UserEntity userSaved = userRepository.save(user);

        if(userSaved==null) throw new RuntimeException("User cant be saved");

        logger.info("User with email: " + userSaved.getEmail() + "is saved to DB");

        return userMapper.createUserEntityDtoFromUserEntity(userSaved);
    }



}
