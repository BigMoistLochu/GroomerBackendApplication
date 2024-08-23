package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.services.AuthenticationService;
import aplikacja.groomerbackend.services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService service;


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequestDto request, HttpServletResponse response){
        String jwtToken = authenticationService.validateAndGenerateBearerToken(request);
        //rozwiazanie z cookie secure dla frontendu: gdy przegladarka dostanie takie ciastko to przy ponownym requescie wysle je tylko przez https albo localhost
        //takie rozwiazanie jest bezpieczniejsze niz przechowywanie w localstorage klienta,
//        Cookie cookie = new Cookie("jwt", jwtToken);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthRequestDto request){
            UserEntityDto user = authenticationService.validateAndRegisterUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
