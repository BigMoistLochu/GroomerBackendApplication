package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.entity.UserEntity;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtService {

    private final String secretKey = "exampleSecretKey";
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public String generateToken(UserEntity userDetails){

        Date date = new Date();
        Timestamp createdTokenTime = new Timestamp(date.getTime());
        //added 10 minut to createdTokenTime
        long tenMinutesInMillis = 10 * 60 * 1000;
        Timestamp expiredTokenTime = new Timestamp(createdTokenTime.getTime()+tenMinutesInMillis);

        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .withClaim("email",userDetails.getEmail())
                .withClaim("avatar",userDetails.getAvatar())
                .withClaim("role",userDetails.getRole().name())
                .withClaim("createTime", createdTokenTime)
                .withClaim("expiredTime",expiredTokenTime)
                .sign(algorithm);
    }


    public boolean validateToken(String token) {
        try {
            //weryfikator:
            JWTVerifier verifier = JWT.require(algorithm).build();
            //weryfikacja tokenu

            DecodedJWT verifiedJwt = verifier.verify(token);
            // porownanie podpisu:
            String originalSignature = JWT.decode(token).getSignature();
            String verifiedSignature = verifiedJwt.getSignature();

            return verifiedSignature.equals(originalSignature);
        } catch (JWTVerificationException exception) {
            return false;
        }
    }









}
