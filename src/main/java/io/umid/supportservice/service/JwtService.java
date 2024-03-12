package io.umid.supportservice.service;

import io.umid.supportservice.dto.JwtResponse;

public interface JwtService {

    JwtResponse login(String username, String password);

    JwtResponse refreshToken(String refreshToken);

}
