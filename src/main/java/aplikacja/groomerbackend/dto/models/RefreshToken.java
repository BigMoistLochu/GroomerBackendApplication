package aplikacja.groomerbackend.dto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {

    @JsonProperty("refresh_token")
    private String refreshToken;
}
