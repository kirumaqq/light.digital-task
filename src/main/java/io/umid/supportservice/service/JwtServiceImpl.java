package io.umid.supportservice.service;

import io.umid.supportservice.dto.JwtResponse;
import io.umid.supportservice.security.AccessToken;
import io.umid.supportservice.security.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final Function<UserDetails, RefreshToken> refreshTokenFactory;

    private final Function<RefreshToken, AccessToken> accessTokenFactory;

    private final Function<AccessToken, String> accessTokenSerializer;

    private final Function<RefreshToken, String> refreshTokenSerializer;

    private final Function<String, RefreshToken> refreshTokenDeserializer;

    @Override
    public JwtResponse receiveTokens(UserDetails userDetails) {

        log.debug("Providing JWT tokens for user: {}", userDetails.getUsername());

        RefreshToken refreshToken = refreshTokenFactory.apply(userDetails);
        AccessToken accessToken = accessTokenFactory.apply(refreshToken);

        return new JwtResponse(
                accessTokenSerializer.apply(accessToken),
                refreshTokenSerializer.apply(refreshToken)
        );
    }

    @Override
    public JwtResponse refreshToken(String refreshTokenStr) {
        log.debug("Refreshing JWT token");

        RefreshToken oldToken = refreshTokenDeserializer.apply(refreshTokenStr);

        if (oldToken != null) {
            AccessToken accessToken = accessTokenFactory.apply(oldToken);
            RefreshToken refreshToken = updateRefreshToken(oldToken);

            return new JwtResponse(
                    accessTokenSerializer.apply(accessToken),
                    refreshTokenSerializer.apply(refreshToken)
            );
        }

        return null;
    }

    private RefreshToken updateRefreshToken(RefreshToken oldToken) {

        log.debug("Updating refresh token");
        log.debug("Old token issued time: {}, expiry: {}", oldToken.issued(), oldToken.expiresAt());

        Duration tokenTtl = getDiffBetweenDates(oldToken.issued(), oldToken.expiresAt());
        log.debug("Calculated refresh token ttl: {}", tokenTtl);

        Instant now = Instant.now();
        Instant expiry = now.plus(tokenTtl);


        return new RefreshToken(
                oldToken.id(),
                oldToken.subject(),
                Date.from(now),
                Date.from(expiry)
        );
    }

    private Duration getDiffBetweenDates(Date before, Date after) {
        return Duration.between(before.toInstant(), after.toInstant());
    }
}
