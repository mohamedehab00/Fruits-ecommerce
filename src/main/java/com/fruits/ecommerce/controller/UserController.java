package com.fruits.ecommerce.controller;

import com.fruits.ecommerce.service.UserService;
import com.fruits_openapi.ecommerce_openapi.api.UserApi;
import com.fruits_openapi.ecommerce_openapi.model.UserDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RequestMapping("/api/v1")
@RestController
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> findUserByUserId(Long id) {
        UserDto user = getUserService().getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
