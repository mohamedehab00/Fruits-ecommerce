package com.fruits.ecommerce.controller;

import com.fruits.ecommerce.domain.User;
import com.fruits.ecommerce.service.JWT_Service.JwtService;
import com.fruits.ecommerce.service.serviceImpl.AuthService;
import com.fruits_openapi.ecommerce_openapi.api.AuthApi;
import com.fruits_openapi.ecommerce_openapi.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<DefaultResponse> registerUser(UserCreationDto userCreationDto) {
        UserDto userDto = this.authService.signup(userCreationDto);

        DefaultResponse response = new DefaultResponse();

        if (userDto.getId() != null) {
            response.message("user created successfully");
            response.code("1000");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.message("email already exists");
        response.code("1001");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<LoginUserSuccessfulResponse> loginUser(UserLoginDto userLoginDto) {
        User user = this.authService.authenticate(userLoginDto);

        //Throws exception through exception advice

        LoginUserSuccessfulResponse response = new LoginUserSuccessfulResponse();

        response.code("0000");
        response.message("user logged in successfully");
        response.setToken(this.jwtService.generateToken(user));
        response.setExpiresIn(this.jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<RoleDto>> findAllRoles() {
        return new ResponseEntity<>(this.authService.getRoles(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addNewRoles(List<@Valid RoleCreationDto> roleCreationDto) {
        this.authService.addNewRoles(roleCreationDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
