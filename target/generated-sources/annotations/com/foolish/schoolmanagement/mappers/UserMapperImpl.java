package com.foolish.schoolmanagement.mappers;

import com.foolish.schoolmanagement.DTOs.UserDTO;
import com.foolish.schoolmanagement.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T12:05:03+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId( user.getUserId() );
        userDTO.setName( user.getName() );
        userDTO.setMobileNum( user.getMobileNum() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setImg( user.getImg() );
        userDTO.setRoles( user.getRoles() );

        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDTO.getUserId() );
        user.setName( userDTO.getName() );
        user.setMobileNum( userDTO.getMobileNum() );
        user.setEmail( userDTO.getEmail() );
        user.setImg( userDTO.getImg() );
        user.setRoles( userDTO.getRoles() );

        return user;
    }
}
