package io.umid.supportservice.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class BearerAuthenticationConverter implements AuthenticationConverter {


    private final Function<String, AccessToken> accessTokenDeserializer;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String tokenRaw = authorization.substring(7);
            AccessToken accessToken = accessTokenDeserializer.apply(tokenRaw);

            if (accessToken != null) {
                log.debug("Deserializer access token of subject: {}", accessToken.subject());

                request.setAttribute("authByJwt", "true");

                return UsernamePasswordAuthenticationToken
                        .unauthenticated(accessToken.subject(), null);
            }

            log.debug("Couldn't deserialize bearer token");
        }

        return null;
    }
}
