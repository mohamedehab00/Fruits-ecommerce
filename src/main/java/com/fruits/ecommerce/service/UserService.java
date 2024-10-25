package com.fruits.ecommerce.service;

import com.fruits_openapi.ecommerce_openapi.model.RoleCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.RoleDto;
import com.fruits_openapi.ecommerce_openapi.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByEmail(String email);
    UserDto getUserById(Long id);
    List<RoleDto> getUserRolesByUserId(Long id);
    void addRolesToSpecificUser(Long userId, List<RoleCreationDto> roles);
}
