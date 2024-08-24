package aplikacja.groomerbackend.services.validators;


import aplikacja.groomerbackend.dto.AuthCredentialsRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthRequestDtoValidatorTest {

    private final AuthRequestDtoValidator authRequestDtoValidator = new AuthRequestDtoValidator();


    @Test
    void shouldReturnTrueWhenEmailIsCorrect(){
        // given
        String email = "examplemail@wp.pl";
        // when
        boolean result = authRequestDtoValidator.isEmailValid(email);
        // then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenEmailIsNull(){
        // given
        String email = null;
        // when
        boolean result = authRequestDtoValidator.isEmailValid(email);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenEmailIsBlank(){
        // given
        String email = "";
        // when
        boolean result = authRequestDtoValidator.isEmailValid(email);
        // then
        Assertions.assertFalse(result);

    }

    @Test
    void shouldReturnFalseWhenEmailIsAtSignIsMissing(){
        // given
        String email = "counterexample.pl";
        // when
        boolean result = authRequestDtoValidator.isEmailValid(email);
        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenPasswordIsValid() {
        // given
        String password = "ValidPass123";

        // when
        boolean result = authRequestDtoValidator.isPasswordValid(password);

        // then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        // given
        String password = null;

        // when
        boolean result = authRequestDtoValidator.isPasswordValid(password);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsBlank() {
        // given
        String password = "   ";

        // when
        boolean result = authRequestDtoValidator.isPasswordValid(password);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsTooShort() {
        // given
        String password = "Short1";

        // when
        boolean result = authRequestDtoValidator.isPasswordValid(password);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsTooLong() {
        // given
        String password = "ThisPasswordIsWayTooLongAndShouldFail1234567890";

        // when
        boolean result = authRequestDtoValidator.isPasswordValid(password);

        // then
        Assertions.assertFalse(result);
    }

    // Tests for isUsernameValid
    @Test
    void shouldReturnTrueWhenUsernameIsValid() {
        // given
        String username = "ValidUser";

        // when
        boolean result = authRequestDtoValidator.isUsernameValid(username);

        // then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenUsernameIsNull() {
        // given
        String username = null;

        // when
        boolean result = authRequestDtoValidator.isUsernameValid(username);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUsernameIsTooShort() {
        // given
        String username = "ab";

        // when
        boolean result = authRequestDtoValidator.isUsernameValid(username);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUsernameIsTooLong() {
        // given
        String username = "ThisUsernameIsWayTooLongForValidationnnnnnnnnnnnnnnnnnnnnnnn";

        // when
        boolean result = authRequestDtoValidator.isUsernameValid(username);

        // then
        Assertions.assertFalse(result);
    }


    @Test
    void shouldReturnTrueWhenLoginRequestIsValid() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "examplemail@wp.pl", "ValidPass123");

        // when
        boolean result = authRequestDtoValidator.validateLoginRequest(authCredentialsRequestDto);

        // then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenEmailIsInvalidInLoginRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "invalid-email", "ValidPass123");

        // when
        boolean result = authRequestDtoValidator.validateLoginRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsInvalidInLoginRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "examplemail@wp.pl", "short");

        // when
        boolean result = authRequestDtoValidator.validateLoginRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }

    // Tests for validateRegistrationRequest
    @Test
    void shouldReturnTrueWhenRegistrationRequestIsValid() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "examplemail@wp.pl", "ValidPass123");

        // when
        boolean result = authRequestDtoValidator.validateRegistrationRequest(authCredentialsRequestDto);

        // then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenEmailIsInvalidInRegistrationRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "invalid-email", "ValidPass123");

        // when
        boolean result = authRequestDtoValidator.validateRegistrationRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsInvalidInRegistrationRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ValidUser", "examplemail@wp.pl", "short");

        // when
        boolean result = authRequestDtoValidator.validateRegistrationRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUsernameIsInvalidInRegistrationRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ab", "examplemail@wp.pl", "ValidPass123");

        // when
        boolean result = authRequestDtoValidator.validateRegistrationRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenAllFieldsAreInvalidInRegistrationRequest() {
        // given
        AuthCredentialsRequestDto authCredentialsRequestDto = new AuthCredentialsRequestDto("ab", "invalid-email", "short");

        // when
        boolean result = authRequestDtoValidator.validateRegistrationRequest(authCredentialsRequestDto);

        // then
        Assertions.assertFalse(result);
    }




}
