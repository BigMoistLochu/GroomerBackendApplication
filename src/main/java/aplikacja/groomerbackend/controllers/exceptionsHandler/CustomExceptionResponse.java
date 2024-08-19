package aplikacja.groomerbackend.controllers.exceptionsHandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomExceptionResponse {

    @JsonProperty("error")
    private HttpStatus status;
    private String message;

}
