package io.umid.supportservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessTokenJwsDeserializer implements Function<String, AccessToken> {

    private final JWSVerifier jwsVerifier;

    @Override
    public AccessToken apply(String tokenRaw) {

        log.debug("Deserializing into access token following JWS");

        try {
            SignedJWT signedJWT = SignedJWT.parse(tokenRaw);
            JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();

            if (hasNotExpired(jwtClaimsSet) && signedJWT.verify(jwsVerifier)) {
                return new AccessToken(
                        UUID.fromString(jwtClaimsSet.getJWTID()),
                        jwtClaimsSet.getSubject(),
                        jwtClaimsSet.getExpirationTime()
                );
            }
        } catch (ParseException e) {
            log.warn("Exception during parsin access token. Invalid token: {}", tokenRaw);
        } catch (JOSEException e) {
            log.warn("Exception during verifying access token. Invalid token: {}", tokenRaw);
        }
        return null;
    }

    private boolean hasNotExpired(JWTClaimsSet jwtClaimsSet) {
        Date expiry = jwtClaimsSet.getExpirationTime();
        log.debug("Expiration time of jws access token: {}", expiry);
        return expiry.after(Date.from(Instant.now()));
    }
}
