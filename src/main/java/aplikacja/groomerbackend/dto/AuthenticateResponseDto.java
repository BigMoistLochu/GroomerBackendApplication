package aplikacja.groomerbackend.dto;



import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
public class AuthenticateResponseDto {

    private String access_token;
    private String refresh_token;

    public AuthenticateResponseDto(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
