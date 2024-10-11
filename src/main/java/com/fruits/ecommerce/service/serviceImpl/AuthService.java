package com.fruits.ecommerce.service.serviceImpl;

import com.fruits.ecommerce.domain.Role;
import com.fruits.ecommerce.domain.User;
import com.fruits.ecommerce.mapper.RoleRoleDtoMapper;
import com.fruits.ecommerce.mapper.UserUserDtoMapper;
import com.fruits.ecommerce.repository.RoleRepository;
import com.fruits.ecommerce.repository.UserRepository;
import com.fruits_openapi.ecommerce_openapi.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserUserDtoMapper userMapper;
    private final RoleRoleDtoMapper roleMapper;

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
                .roles(validRoles.stream().toList())
                .createdAt(null)
                .updatedAt(null)
                .build();

        user = this.userRepository.save(user);

        return this.userMapper.userToUserDto(user);
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
        return roles.stream().map(this.roleMapper::roleToRoleDto).toList();
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
                    .type("ROLE_" + roleCreationDto.getType())
                    .createdAt(null)
                    .updatedAt(null)
                    .build();

            this.roleRepository.save(role);
        }
    }
}
