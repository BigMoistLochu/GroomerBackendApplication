package aplikacja.groomerbackend.mappers;

import aplikacja.groomerbackend.dto.AuthRequestDto;
import aplikacja.groomerbackend.dto.UserEntityDto;
import aplikacja.groomerbackend.entity.UserEntity;

public class UserMapper {


    public UserEntity getUserEntityFromAuthRequestDto(AuthRequestDto authRequestDto){
        return new UserEntity(authRequestDto.getUsername(),authRequestDto.getEmail(),authRequestDto.getPassword());
    }

    public UserEntityDto createUserEntityDtoFromUserEntity(UserEntity user){
        return new UserEntityDto(user.getId(), user.getEmail(), user.getUsername(), user.getAvatar());
    }




}
