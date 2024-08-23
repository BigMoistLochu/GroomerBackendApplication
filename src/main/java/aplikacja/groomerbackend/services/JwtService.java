package aplikacja.groomerbackend.services;


import aplikacja.groomerbackend.entity.UserEntity;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


@Service
public class JwtService {

    private final String secretKey = "exampleSecretKey";
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    /**
     * Generate accesToken for user and return it to the client
     * @param userDetails from AuthRequestDto
     * @return
     */
    public String generateAccessTokenForUser(UserEntity userDetails){

        Date date = new Date();
        Timestamp createdTokenTime = new Timestamp(date.getTime());
        Timestamp expiredTokenTime = getTokenExpirationTime(createdTokenTime);
        return generateBearerToken(createdTokenTime,expiredTokenTime,userDetails);
    }

    /**
     * If token signature is correct and token is not expired return true, otherwise false
     * @param token from client
     * @return boolean
     */
    public boolean validateToken(String token) {
        try {
            //weryfikacja tokenu
            DecodedJWT verifiedJwt = JWT.require(algorithm).build().verify(token);

            //porownanie podpisu:
            String verifiedSignature = verifiedJwt.getSignature();
            String originalSignature = JWT.decode(token).getSignature();


            Map<String,Claim> claimMap = verifiedJwt.getClaims();
            if(!(claimMap.containsKey("createTime") && claimMap.containsKey("expiredTime"))) return false;

            Date actualDate = new Date();
            Date expiredTime = claimMap.get("expiredTime").asDate();
            if(actualDate.after(expiredTime)) return false;

            return verifiedSignature.equals(originalSignature);
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String getEmailFromToken(String token){

        if(!validateToken(token)) return null;

        Map<String,Claim> claimMap = JWT.decode(token).getClaims();
        if(!claimMap.containsKey("email")) return null;

        return claimMap.get("email").asString();
    }

    protected String generateBearerToken(Timestamp createdTokenTime,Timestamp expiredTokenTime,UserEntity userDetails){

        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .withClaim("email",userDetails.getEmail())
                .withClaim("avatar",userDetails.getAvatar())
                .withClaim("role",userDetails.getRole().name())
                .withClaim("createTime", createdTokenTime)
                .withClaim("expiredTime",expiredTokenTime)
                .sign(algorithm);
    }

    protected Timestamp getTokenExpirationTime(Timestamp currentTime){
        //added 10 minut to createdTokenTime
        long tenMinutesInMillis = 10 * 60 * 1000;
        return new Timestamp(currentTime.getTime()+tenMinutesInMillis);
    }












}
