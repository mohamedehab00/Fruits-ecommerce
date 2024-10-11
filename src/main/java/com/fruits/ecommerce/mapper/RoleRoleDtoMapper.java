package com.fruits.ecommerce.mapper;

import com.fruits.ecommerce.domain.Role;
import com.fruits_openapi.ecommerce_openapi.model.RoleCreationDto;
import com.fruits_openapi.ecommerce_openapi.model.RoleDto;
import org.mapstruct.Mapper;

@Mapper
public interface RoleRoleDtoMapper {
    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);
    Role roleCreationDtoToRole(RoleCreationDto roleCreationDto);
}
