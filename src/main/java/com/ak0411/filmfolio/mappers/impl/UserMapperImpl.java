package com.ak0411.filmfolio.mappers.impl;

import com.ak0411.filmfolio.domain.dtos.UserDto;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements Mapper<User, UserDto> {

    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public List<UserDto> mapAll(Collection<User> userList) {
        return userList.stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }

}
