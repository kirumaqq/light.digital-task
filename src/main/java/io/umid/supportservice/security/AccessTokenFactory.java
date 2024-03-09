package io.umid.supportservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class AccessTokenFactory implements Function<RefreshToken, AccessToken> {

    private final Duration accessTokenTtl;

    @Override
    public AccessToken apply(RefreshToken refreshToken) {

        Date expiry = Date.from(Instant.now().plus(accessTokenTtl));

        return new AccessToken(
                refreshToken.id(),
                refreshToken.subject(),
                expiry);
    }
}
