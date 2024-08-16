package aplikacja.groomerbackend.mappers;

import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.entity.UserEntity;

public class UserMapper {


    public UserEntity getUserEntityFromAuthRequestDto(AuthRequestDto authRequestDto){
        return new UserEntity(authRequestDto.getUsername(),authRequestDto.getEmail(),authRequestDto.getPassword());
    }




}
