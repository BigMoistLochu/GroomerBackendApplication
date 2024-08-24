package aplikacja.groomerbackend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserEntityDto {

    private Long id;
    private String email;
    private String username;
    private String avatar;
}
