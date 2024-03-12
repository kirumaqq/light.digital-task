package io.umid.supportservice.controller;

import io.umid.supportservice.dto.EditUserDto;
import io.umid.supportservice.dto.GetUserDto;
import io.umid.supportservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PatchMapping("/user/{id}")
    public GetUserDto editUser(EditUserDto editUserDto) {
        log.info("Editing user: {}", editUserDto);

        return userService.addRoleToUser(editUserDto);
    }


}
