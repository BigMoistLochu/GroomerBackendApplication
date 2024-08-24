package aplikacja.groomerbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentialsRequestDto {

    private String username;
    private String email;
    private String password;
}
