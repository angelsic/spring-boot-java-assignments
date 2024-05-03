package org.adaschool.api.mapper;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;

public class UserMapper {
    public static UserDto mapToUserDto (User user){
        return new UserDto(
                user.getName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static User mapToUser (UserDto userDto){
        return new User(userDto);
    }
}
