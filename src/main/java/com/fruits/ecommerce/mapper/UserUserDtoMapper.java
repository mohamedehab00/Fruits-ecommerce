package com.fruits.ecommerce.mapper;

import com.fruits.ecommerce.domain.User;
import com.fruits_openapi.ecommerce_openapi.model.UserCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserUserDtoMapper {
    User usertDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    @Mapping(target = "roles", source = "roles", ignore = true)
    User userCreationDtoToUser(UserCreationDto userCreationDto);
}
