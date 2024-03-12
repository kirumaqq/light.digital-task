package io.umid.supportservice.service;

import io.umid.supportservice.dto.EditUserDto;
import io.umid.supportservice.dto.GetUserDto;

public interface UserService {

    GetUserDto addRoleToUser(EditUserDto editUserDto);
}
