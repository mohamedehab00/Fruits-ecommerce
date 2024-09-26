package com.fruits.ecommerce.controller;

import com.fruits.ecommerce.domain.User;
import com.fruits.ecommerce.service.serviceImpl.AuthService;
import com.fruits.ecommerce.service.JWT_Service.JwtService;
import com.fruits_openapi.ecommerce_openapi.api.AuthApi;
import com.fruits_openapi.ecommerce_openapi.model.*;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@RestController
@RequestMapping("/api/v1")
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity registerUser(UserCreationDto userCreationDto) {
        UserDto userDto = getAuthService().signup(userCreationDto);

        if (userDto.getId() != null) {
            RegisterUser201Response response = new RegisterUser201Response();
            response.message("user created successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        RegisterUser400Response response = new RegisterUser400Response();
        response.message("email already exists");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<LoginUser200Response> loginUser(UserLoginDto userLoginDto) {
        User user = getAuthService().authenticate(userLoginDto);

        //Throws exception

        LoginUser200Response response = new LoginUser200Response();

        response.setToken(getJwtService().generateToken(user));
        response.setExpiresIn(getJwtService().getExpirationTime());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RoleDto>> findAllRoles() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

/*        if(!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
*/
        return new ResponseEntity<>(getAuthService().getRoles(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addNewRoles(List<@Valid RoleCreationDto> roleCreationDto) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /*if(!authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }*/

        getAuthService().addNewRoles(roleCreationDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
