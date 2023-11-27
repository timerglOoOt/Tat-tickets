package tat_tickets.utils.mappers.impl;

import tat_tickets.dto.SignUpForm;
import tat_tickets.dto.UserDto;
import tat_tickets.models.User;
import tat_tickets.utils.mappers.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatarId(user.getAvatarId())
                .build();
    }

    @Override
    public User toUser(UserDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .avatarId(dto.getAvatarId())
                .build();
    }

    @Override
    public User toUser(SignUpForm dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .hashedPassword(dto.getPassword())
                .build();
    }
}
