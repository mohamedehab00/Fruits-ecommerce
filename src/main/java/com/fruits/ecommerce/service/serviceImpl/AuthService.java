package com.fruits.ecommerce.service.serviceImpl;

import com.fruits.ecommerce.domain.Role;
import com.fruits.ecommerce.domain.User;
import com.fruits.ecommerce.repository.RoleRepository;
import com.fruits.ecommerce.repository.UserRepository;
import com.fruits_openapi.ecommerce_openapi.model.*;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UserDto signup(UserCreationDto input) {
        HashSet<Role> validRoles = new HashSet<>();

        for (Long roleId : input.getRoles()) {
            Optional<Role> role = roleRepository.findById(roleId);

            //Throw Exception If Not Found

            role.ifPresent(validRoles::add);
        }

        User user = User.builder()
                .id(null)
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .roles(validRoles)
                .createdAt(null)
                .updatedAt(null)
                .build();

        user = userRepository.save(user);

        return getModelMapper().map(user, UserDto.class);
    }

    public User authenticate(UserLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(
                role -> getModelMapper().map(role, RoleDto.class)
        ).toList();
    }

    @Transactional
    public void addNewRoles(List<RoleCreationDto> rolesCreationDto) {
        if (rolesCreationDto.isEmpty()) {
            throw new RuntimeException("Roles cannot be empty");
        }
        HashSet<RoleCreationDto> validRoles = new HashSet<>(rolesCreationDto);

        for (RoleCreationDto roleCreationDto : validRoles) {
            Role role = Role.builder()
                    .id(null)
                    .type("ROLE_"+roleCreationDto.getType())
                    .createdAt(null)
                    .updatedAt(null)
                    .build();

            getRoleRepository().save(role);
        }
    }
}
