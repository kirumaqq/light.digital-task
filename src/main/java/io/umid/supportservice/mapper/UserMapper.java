package io.umid.supportservice.mapper;

import io.umid.supportservice.dto.GetUserDto;
import io.umid.supportservice.model.Role;
import io.umid.supportservice.model.Roles;
import io.umid.supportservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    GetUserDto toDto(User user);

    default Roles toRoles(Role role) {
        return role.getName();
    }

}
