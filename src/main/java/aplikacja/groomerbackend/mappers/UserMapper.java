package aplikacja.groomerbackend.mappers;

import aplikacja.groomerbackend.dto.AuthCredentialsRequestDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.entity.Role;
import aplikacja.groomerbackend.entity.UserEntity;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class UserMapper {


    public UserEntity getUserEntityFromAuthRequestDto(AuthCredentialsRequestDto authCredentialsRequestDto){
        return new UserEntity(authCredentialsRequestDto.getUsername(), authCredentialsRequestDto.getEmail(), authCredentialsRequestDto.getPassword());
    }

    public UserEntityDto createUserEntityDtoFromUserEntity(UserEntity user){
        return new UserEntityDto(user.getId(), user.getEmail(), user.getUsername(), user.getAvatar());
    }

    public UserEntity getUserEntityFromClaimsMap(Map<String, Claim> claimMap){
        String username = claimMap.get("username").asString();
        String email = claimMap.get("email").asString();
        String avatar = claimMap.get("avatar").asString();
        String role = claimMap.get("role").asString();
        return new UserEntity(username,email,avatar, Role.valueOf(role));
    }





}
