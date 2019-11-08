package com.greeneyed.samples.webservices.restfulwebservice.mapper;

import com.greeneyed.samples.webservices.restfulwebservice.dto.UserDto;
import com.greeneyed.samples.webservices.restfulwebservice.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> entityList);
}
