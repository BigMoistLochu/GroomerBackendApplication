package aplikacja.groomerbackend.services;

import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldReturnTrueWhenTokenIsCorrect(){
       //given
       String correct_jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImtvbnJhZGQiLCJlbWFpbCI6ImtvbnJhZEtvcGthQHdwLnBsIiwiYXZhdGFyIjpudWxsLCJyb2xlIjoiQURNSU4iLCJjcmVhdGVUaW1lIjoxNzI0MTQ1Mjc1LCJleHBpcmVkVGltZSI6MTcyNDE0NTg3NX0.XJXu5L28VNCKfnYu3Rmjx-EnsUVOkyRkiMnEhaxtFqw";
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
        String result_token = jwtService.generateToken(user);
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






}
