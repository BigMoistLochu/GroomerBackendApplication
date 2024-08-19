package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.exceptions.UserAlreadyExistsException;
import aplikacja.groomerbackend.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequestDto request){
            String JwtToken = authenticationService.validateAndGenerateLoginToken(request);
            return ResponseEntity.ok(JwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthRequestDto request){
            UserEntityDto user = authenticationService.validateAndRegisterUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}
