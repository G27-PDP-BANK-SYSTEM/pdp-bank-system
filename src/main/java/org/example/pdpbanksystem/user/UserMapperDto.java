package org.example.pdpbanksystem.user;

import lombok.RequiredArgsConstructor;
import org.example.pdpbanksystem.common.service.GenericDtoMapper;
import org.example.pdpbanksystem.user.dto.UserCreateDto;
import org.example.pdpbanksystem.user.dto.UserResponseDto;
import org.example.pdpbanksystem.user.dto.UserUpdateDto;
import org.example.pdpbanksystem.user.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperDto extends GenericDtoMapper<User, UserCreateDto, UserUpdateDto, UserResponseDto> {
    private final ModelMapper mapper;

    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        return mapper.map(userCreateDto, User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, User user) {
        mapper.map(userUpdateDto, user);
    }


}
