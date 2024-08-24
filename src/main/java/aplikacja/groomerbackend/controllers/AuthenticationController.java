package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthCredentialsRequestDto;
import aplikacja.groomerbackend.dto.AuthenticateResponseDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.dto.models.RefreshToken;
import aplikacja.groomerbackend.services.AuthenticationService;
import aplikacja.groomerbackend.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponseDto> loginUser(@RequestBody AuthCredentialsRequestDto request){
        AuthenticateResponseDto jwtTokenAndRefreshToken = authenticationService.validateAndGenerateBearerToken(request);
        return ResponseEntity.ok(jwtTokenAndRefreshToken);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthCredentialsRequestDto request){
            UserEntityDto user = authenticationService.validateAndRegisterUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> generateRefreshToken(@RequestBody RefreshToken refreshToken){

        AuthenticateResponseDto authenticateResponse = authenticationService.regenerateRefreshAndBearerToken(refreshToken.getRefreshToken());
        return ResponseEntity.ok(authenticateResponse);
    }

}
