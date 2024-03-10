package io.umid.supportservice.service;

import io.umid.supportservice.dto.JwtResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    JwtResponse receiveTokens(UserDetails userDetails);

    JwtResponse refreshToken(String refreshToken);

}
