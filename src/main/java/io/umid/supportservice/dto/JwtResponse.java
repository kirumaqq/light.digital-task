package io.umid.supportservice.dto;

public record JwtResponse(
        String accessToken,
        String refreshToken
) {
}
