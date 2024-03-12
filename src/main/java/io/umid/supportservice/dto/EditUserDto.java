package io.umid.supportservice.dto;

import io.umid.supportservice.model.Roles;

public record EditUserDto(
        Integer id,
        Roles role
) {
}
