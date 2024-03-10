package io.umid.supportservice.controller;

import io.umid.supportservice.dto.JwtResponse;
import io.umid.supportservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    @GetMapping("/token")
    public JwtResponse getTokens(@AuthenticationPrincipal UserDetails userDetails) {
        return jwtService.receiveTokens(userDetails);
    }

    @GetMapping("/refresh")
    public JwtResponse refresh(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth) {


        if (auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            JwtResponse jwtResponse = jwtService.refreshToken(token);

            if (jwtResponse != null) {
                return jwtResponse;
            }
        }

        throw new AccessDeniedException("Refresh token is invalid");
    }
}
