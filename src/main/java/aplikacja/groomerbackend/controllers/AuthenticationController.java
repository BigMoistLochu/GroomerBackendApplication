package aplikacja.groomerbackend.controllers;


import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequestDto request){
        try {
            String JwtToken = authenticationService.loginUser(request);

            return ResponseEntity.status(200).body(JwtToken);

        }catch(IllegalArgumentException illegalArgumentException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(illegalArgumentException.getMessage());
        }catch (UsernameNotFoundException usernameNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usernameNotFoundException.getMessage());
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    public String registerUser(@RequestBody AuthRequestDto request){


        return "registered user";
    }



}
