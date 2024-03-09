package io.umid.supportservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
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
public class RefreshTokenJweDeserializer implements Function<String, RefreshToken> {

    private final JWEDecrypter jweDecrypter;

    @Override
    public RefreshToken apply(String s) {

        try {
            EncryptedJWT encryptedJWT = EncryptedJWT.parse(s);
            encryptedJWT.decrypt(jweDecrypter);
            JWTClaimsSet jwtClaimsSet = encryptedJWT.getJWTClaimsSet();

            if (hasNotExpired(jwtClaimsSet)) {
                return new RefreshToken(
                        UUID.fromString(jwtClaimsSet.getJWTID()),
                        jwtClaimsSet.getSubject(),
                        jwtClaimsSet.getIssueTime(),
                        jwtClaimsSet.getExpirationTime()
                );
            }


        } catch (ParseException e) {
            log.warn("Exception during verifying refresh token. Invalid token: {}", s);
        } catch (JOSEException e) {
            log.warn("Exception during decrypting refresh token. Invalid token: {}", s);
        }

        return null;
    }

    private boolean hasNotExpired(JWTClaimsSet jwtClaimsSet) {
        Date expiry = jwtClaimsSet.getExpirationTime();
        log.debug("Expiration time of jwe access token: {}", expiry);
        return expiry.after(Date.from(Instant.now()));
    }
}
