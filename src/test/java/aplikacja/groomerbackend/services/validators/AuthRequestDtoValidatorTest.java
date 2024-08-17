package aplikacja.groomerbackend.services.validators;


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




}
