package io.umid.supportservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
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
    public AccessToken apply(String s) {

        log.debug("Deserializing into access token following JWS");

        try {
            SignedJWT signedJWT = SignedJWT.parse(s);
            JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();

            if (hasNotExpired(jwtClaimsSet) && signedJWT.verify(jwsVerifier)) {

                return new AccessToken(
                        UUID.fromString(jwtClaimsSet.getJWTID()),
                        jwtClaimsSet.getSubject(),
                        jwtClaimsSet.getExpirationTime()
                );
            }

            return null;
        } catch (ParseException e) {
            log.warn("Exception during parsin access token. Invalid token: {}", s);
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            log.warn("Exception during verifying access token. Invalid token: {}", s);
            throw new RuntimeException(e);
        }

    }

    private boolean hasNotExpired(JWTClaimsSet jwtClaimsSet) {
        Date expiry = jwtClaimsSet.getExpirationTime();
        log.debug("Expiration time of jws access token: {}", expiry);
        return expiry.before(Date.from(Instant.now()));
    }
}
