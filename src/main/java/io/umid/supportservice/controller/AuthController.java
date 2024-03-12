package io.umid.supportservice.controller;

import io.umid.supportservice.dto.JwtResponse;
import io.umid.supportservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(String username, String password) {
        return authService.login(username, password);
    }

    @GetMapping("/refresh")
    public JwtResponse refresh(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth) {

        var token = auth.substring(7);

        return authService.refreshToken(token);
    }
}
