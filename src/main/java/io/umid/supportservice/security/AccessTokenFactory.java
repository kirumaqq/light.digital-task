package io.umid.supportservice.security;

import io.umid.supportservice.config.JwtProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;


@Component
public class AccessTokenFactory implements Function<RefreshToken, AccessToken> {

    private final Duration accessTokenTtl;

    public AccessTokenFactory(JwtProperties jwtProperties) {
        this.accessTokenTtl = jwtProperties.getAccessTokenTtl();
    }

    @Override
    public AccessToken apply(RefreshToken refreshToken) {

        Date expiry = Date.from(Instant.now().plus(accessTokenTtl));

        return new AccessToken(
                refreshToken.id(),
                refreshToken.subject(),
                expiry);
    }
}
