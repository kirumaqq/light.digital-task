package io.umid.supportservice.security;

import io.umid.supportservice.config.JwtProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;


@Component
public class RefreshTokenFactory implements Function<UserDetails, RefreshToken> {

    private final Duration refreshTokenTtl;

    public RefreshTokenFactory(JwtProperties jwtProperties) {
        this.refreshTokenTtl = jwtProperties.getRefreshTokenTtl();
    }

    @Override
    public RefreshToken apply(UserDetails userDetails) {

        Instant now = Instant.now();
        Instant expiry = now.plus(refreshTokenTtl);

        return new RefreshToken(
                UUID.randomUUID(),
                userDetails.getUsername(),
                Date.from(now),
                Date.from(expiry));
    }
}
