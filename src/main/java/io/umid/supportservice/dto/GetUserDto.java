package io.umid.supportservice.dto;

import io.umid.supportservice.model.Roles;

import java.util.Set;

public record GetUserDto(
        Integer id,
        String username,
        Set<Roles> roles
) {

}
