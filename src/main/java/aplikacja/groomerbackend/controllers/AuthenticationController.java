package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.entity.UserEntity;
import aplikacja.groomerbackend.exceptions.UserAlreadyExistsException;
import aplikacja.groomerbackend.exceptions.UserNotSavedIntoDbException;
import aplikacja.groomerbackend.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequestDto request){
        try {
            String JwtToken = authenticationService.validateUserAndGenerateLoginToken(request);

            return ResponseEntity.status(200).body(JwtToken);

        }catch(IllegalArgumentException illegalArgumentException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(illegalArgumentException.getMessage());
        }catch (UsernameNotFoundException usernameNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usernameNotFoundException.getMessage());
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthRequestDto request){
        try {
            UserEntityDto user = authenticationService.validateAndCreateUser(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        }catch(IllegalArgumentException illegalArgumentException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(illegalArgumentException.getMessage());
        }
        catch(UserAlreadyExistsException userAlreadyExistsException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistsException.getMessage());
        }catch (UserNotSavedIntoDbException userNotSavedIntoDbException){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userNotSavedIntoDbException.getMessage());
        }


    }


}
