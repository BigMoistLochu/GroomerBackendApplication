package aplikacja.groomerbackend.services;

import aplikacja.groomerbackend.dto.AuthenticateResponseDto;
import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldReturnTrueWhenTokenIsCorrect(){
       //given
        UserEntity user = new UserEntity("Januszek","email12345@wp.pl","password12345",null, Role.EMPLOYEE,false);
        AuthenticateResponseDto authenticateResponse = jwtService.generateBearerAndRefreshToken(user);
       //when
       boolean results =  jwtService.validateToken(authenticateResponse.getAccess_token());
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
        AuthenticateResponseDto result_token = jwtService.generateBearerAndRefreshToken(user);
        String expected_email_from_token = jwtService.getEmailFromToken(result_token.getAccess_token());
        //then
        Assertions.assertEquals("email12345@wp.pl",expected_email_from_token);
    }

    @Test
    void getEmailFromTokenMethodshouldReturnNullWhenGeneratedTokenHasInvalidPayload(){
        //given
        UserEntity user = new UserEntity("Januszek", null, "password12345", null, Role.EMPLOYEE, false);
        AuthenticateResponseDto invalid_payload_token = jwtService.generateBearerAndRefreshToken(user);
        //when
        String expected_null_email_from_token = jwtService.getEmailFromToken(invalid_payload_token.getAccess_token());
        //then
        Assertions.assertNull(expected_null_email_from_token);
    }


    @Test
    void shouldReturnFalseForExpiredToken() {
        // given
        UserEntity user = new UserEntity("Januszek", "email12345@wp.pl", "password12345", null, Role.EMPLOYEE, false);
        long fifteenMinutesInMillis = 15 * 60 * 1000;
        Date currentDate = new Date();
        Timestamp generatedTime = new Timestamp(currentDate.getTime() + fifteenMinutesInMillis); // Token generated 15 min later
        Timestamp expirationTime = new Timestamp(currentDate.getTime()); // Token expires now

        // when
        String token = jwtService.generateToken(generatedTime, expirationTime, user);
        boolean isTokenValid = jwtService.validateToken(token);

        // then
        Assertions.assertFalse(isTokenValid, "Token should be expired and the validation should return false");
    }




}
