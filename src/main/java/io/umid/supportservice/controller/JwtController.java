package io.umid.supportservice.controller;

import io.umid.supportservice.dto.JwtResponse;
import io.umid.supportservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public JwtResponse login(String username, String password) {
        return jwtService.login(username, password);
    }

    @GetMapping("/refresh")
    public JwtResponse refresh(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth) {

        var token = auth.substring(7);

        return jwtService.refreshToken(token);
    }
}
