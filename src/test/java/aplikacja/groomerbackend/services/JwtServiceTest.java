package aplikacja.groomerbackend.services;

import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.Date;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldReturnTrueWhenTokenIsCorrect(){
       //given
        UserEntity user = new UserEntity("Januszek","email12345@wp.pl","password12345",null, Role.EMPLOYEE,false);
        String correct_jwt = jwtService.generateAccessTokenForUser(user);
       //when
       boolean results =  jwtService.validateToken(correct_jwt);
       //then
        Assertions.assertTrue(results);
    }


    @Test
    void shouldReturnFalseWhenTokenIsInCorrect(){
        //given
        String incorrect_jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImtvbnJhZGQiLCJlbWFpbCI6ImtvbnJhZEtvcGthQHdwLnBsIiwiYXZhdGFyIjpudWxsLCJyb2xlIjoiQ1VTVE9NRVIiLCJjcmVhdGVUaW1lIjoxNzI0MTQ1Mjc1LCJleHBpcmVkVGltZSI6MTcyNDE0NTg3NX0.dMt1BJnjLHkmJCHOdvBQoUWWzi2iTIegGTq2nn1pfbE";
        //when
        boolean results =  jwtService.validateToken(incorrect_jwt);
        //then
        Assertions.assertFalse(results);
    }

    @Test
    void shouldReturnFalseWhenTokenHasInvalidPayload(){
        //given
        String jwt_with_Invalid_Payload = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.invalid_payload.dMt1BJnjLHkmJCHOdvBQoUWWzi2iTIegGTq2nn1pfbE";
        //when
        boolean results =  jwtService.validateToken(jwt_with_Invalid_Payload);
        //then
        Assertions.assertFalse(results);
    }

    @Test
    void shouldReturnFalseWhenTokenHasInvalidHeader(){
        //given
        String jwt_with_Invalid_Header = "invalid_header.eyJ1c2VybmFtZSI6ImtvbnJhZGQiLCJlbWFpbCI6ImtvbnJhZEtvcGthQHdwLnBsIiwiYXZhdGFyIjpudWxsLCJyb2xlIjoiQ1VTVE9NRVIiLCJjcmVhdGVUaW1lIjoxNzI0MTQ1Mjc1LCJleHBpcmVkVGltZSI6MTcyNDE0NTg3NX0.r0Sp579pO13jLI3aempPvovd2CIwd63sErGq9K1UYiU";
        //when
        boolean results =  jwtService.validateToken(jwt_with_Invalid_Header);
        //then
        Assertions.assertFalse(results);
    }


    @Test
    void shouldReturnFalseWhenTokenHasInvalidSignature(){
        //given
        String jwt_with_Invalid_Signature = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImtvbnJhZGQiLCJlbWFpbCI6ImtvbnJhZEtvcGthQHdwLnBsIiwiYXZhdGFyIjpudWxsLCJyb2xlIjoiQ1VTVE9NRVIiLCJjcmVhdGVUaW1lIjoxNzI0MTQ1Mjc1LCJleHBpcmVkVGltZSI6MTcyNDE0NTg3NX0.badcredientials";
        //when
        boolean results =  jwtService.validateToken(jwt_with_Invalid_Signature);
        //then
        Assertions.assertFalse(results);
    }

    @Test
    void shouldReturnCorrectEmailWhenTokenIsCreated(){
        //given
        UserEntity user = new UserEntity("Januszek","email12345@wp.pl","password12345",null, Role.EMPLOYEE,false);

        //when
        String result_token = jwtService.generateAccessTokenForUser(user);
        String expected_email_from_token = jwtService.getEmailFromToken(result_token);
        //then
        Assertions.assertEquals("email12345@wp.pl",expected_email_from_token);
    }

    @Test
    void getEmailFromTokenMethodshouldReturnNullWhenGeneratedTokenHasInvalidPayload(){
        //given
        String invalid_payload_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.validPayload.r0Sp579pO13jLI3aempPvovd2CIwd63sErGq9K1UYiU";

        //when
        String expected_null_email_from_token = jwtService.getEmailFromToken(invalid_payload_token);
        //then
        Assertions.assertNull(expected_null_email_from_token);
    }


    @Test
    void generatedTokenWithExpiredTokenShouldReturnFalseWhenTokenIsExpired(){
        // given

        UserEntity user = new UserEntity("Januszek", "email12345@wp.pl", "password12345", null, Role.EMPLOYEE, false);
        JwtService mockService = Mockito.mock(JwtService.class);
        Timestamp actualTime = new Timestamp(new Date().getTime());
        long fifteenMinutesInMillis = 15 * 60 * 1000;


        //when
        Mockito.when(mockService.getTokenExpirationTime(actualTime))
                .thenReturn(new Timestamp(actualTime.getTime()-fifteenMinutesInMillis));

        Timestamp expirationTime = mockService.getTokenExpirationTime(actualTime);

        String token = jwtService.generateBearerToken(actualTime,expirationTime,user);

        boolean result_should_be_false = jwtService.validateToken(token);
        // then
        Assertions.assertFalse(result_should_be_false, "Token should be expired and the validation should return false");
    }






}
