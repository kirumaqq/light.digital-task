package io.umid.supportservice.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.function.Function;

@RequiredArgsConstructor
public class BearerAuthenticationConverter implements AuthenticationConverter {


    private final Function<String, AccessToken> accessTokenDeserializer;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String tokenRaw = authorization.substring(7);
            AccessToken accessToken = accessTokenDeserializer.apply(tokenRaw);

            return UsernamePasswordAuthenticationToken
                    .unauthenticated(accessToken.subject(), null);
        }

        return null;
    }
}
