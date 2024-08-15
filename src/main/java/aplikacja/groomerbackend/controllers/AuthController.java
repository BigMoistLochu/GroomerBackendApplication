package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String getLoginToken(@RequestBody AuthRequestDto request){



        return "token";
    }



}
