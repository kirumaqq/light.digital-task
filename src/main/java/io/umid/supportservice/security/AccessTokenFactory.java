package io.umid.supportservice.security;

import io.umid.supportservice.config.JwtProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;


@Component
public class AccessTokenFactory {

    private final Duration accessTokenTtl;

    public AccessTokenFactory(JwtProperties jwtProperties) {
        this.accessTokenTtl = jwtProperties.getAccessTokenTtl();
    }

    public AccessToken createByRefreshToken(RefreshToken refreshToken) {

        Date expiry = Date.from(Instant.now().plus(accessTokenTtl));

        return new AccessToken(
                refreshToken.id(),
                refreshToken.subject(),
                expiry);
    }
}
