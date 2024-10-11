package com.fruits.ecommerce.service.serviceImpl;

import com.fruits.ecommerce.domain.User;
import com.fruits.ecommerce.mapper.UserUserDtoMapper;
import com.fruits.ecommerce.repository.RoleRepository;
import com.fruits.ecommerce.repository.UserRepository;
import com.fruits.ecommerce.service.UserService;
import com.fruits_openapi.ecommerce_openapi.model.RoleCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.RoleDto;
import com.fruits_openapi.ecommerce_openapi.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserUserDtoMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return this.userMapper.userToUserDto(user.get());
    }

    @Override
    public List<RoleDto> getUserRolesByUserId(Long id) {
        return List.of();
    }

    @Override
    public void addRolesToSpecificUser(Long userId, List<RoleCreationDto> roles) {

    }
}
