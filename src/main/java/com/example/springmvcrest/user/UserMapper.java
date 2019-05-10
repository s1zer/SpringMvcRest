package com.example.springmvcrest.user;

public class UserMapper {

    static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setActivated(user.isActivated());
        return userDto;
    }

    static User toEntity(UserDto userDto) {
        User userEntity = new User();
        userEntity.setId(userDto.getId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setActivated(userDto.isActivated());
        return userEntity;
    }
}
