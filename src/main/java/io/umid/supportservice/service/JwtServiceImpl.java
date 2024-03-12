package io.umid.supportservice.service;

import io.umid.supportservice.dto.JwtResponse;
import io.umid.supportservice.exception.InvalidTokenException;
import io.umid.supportservice.security.AccessToken;
import io.umid.supportservice.security.AccessTokenFactory;
import io.umid.supportservice.security.RefreshToken;
import io.umid.supportservice.security.RefreshTokenFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final RefreshTokenFactory refreshTokenFactory;

    private final AccessTokenFactory accessTokenFactory;

    private final Function<AccessToken, String> accessTokenSerializer;

    private final Function<RefreshToken, String> refreshTokenSerializer;

    private final Function<String, RefreshToken> refreshTokenDeserializer;

    private final AuthenticationManager authenticationManager;


    @Override

    public JwtResponse login(String username, String password) {

        log.debug("Signing in user with name: {}", username);
        var userPassToken = UsernamePasswordAuthenticationToken
                .unauthenticated(username, password);

        var authentication = authenticationManager.authenticate(userPassToken);
        log.debug("Got authentication: {}", authentication);

        if (!(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("Principal must be of type user details");
        }


        return generateTokens(userDetails);
    }


    public JwtResponse generateTokens(UserDetails userDetails) {
        log.debug("Generating tokens for user: {}", userDetails.getUsername());

        var refreshToken = refreshTokenFactory.createByUserDetails(userDetails);
        var accessToken = accessTokenFactory.createByRefreshToken(refreshToken);

        return new JwtResponse(
                accessTokenSerializer.apply(accessToken),
                refreshTokenSerializer.apply(refreshToken)
        );
    }


    @Override
    public JwtResponse refreshToken(String refreshTokenStr) {
        log.debug("Refreshing JWT token");

        RefreshToken oldToken = refreshTokenDeserializer.apply(refreshTokenStr);

        if (oldToken == null) {
            throw new InvalidTokenException("Refresh token is invalid: " + refreshTokenStr);
        }

        AccessToken accessToken = accessTokenFactory.createByRefreshToken(oldToken);
        RefreshToken refreshToken = refreshTokenFactory.recreate(oldToken);

        return new JwtResponse(
                accessTokenSerializer.apply(accessToken),
                refreshTokenSerializer.apply(refreshToken)
        );
    }
}
