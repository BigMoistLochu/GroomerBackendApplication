package aplikacja.groomerbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserEntityDto {

    private Long id;
    private String email;
    private String username;
    private String avatar;
}
