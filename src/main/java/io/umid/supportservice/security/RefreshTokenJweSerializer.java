package io.umid.supportservice.security;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class RefreshTokenJweSerializer implements Function<RefreshToken, String> {

    private final JWEAlgorithm jweAlgorithm;

    private final EncryptionMethod encryptionMethod;

    private final JWEEncrypter jweEncrypter;

    @Override
    public String apply(RefreshToken refreshToken) {

        log.debug("Serializing refresh token");

        JWEHeader jweHeader = new JWEHeader.Builder(jweAlgorithm, encryptionMethod)
                .keyID(refreshToken.id().toString())
                .build();

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(refreshToken.id().toString())
                .subject(refreshToken.subject())
                .issueTime(refreshToken.issued())
                .expirationTime(refreshToken.expiresAt())
                .build();

        EncryptedJWT encryptedJWT = new EncryptedJWT(jweHeader, jwtClaimsSet);

        try {
            encryptedJWT.encrypt(jweEncrypter);
            return encryptedJWT.serialize();
        } catch (JOSEException e) {
            log.warn("Internal JOSEException: ", e);
            throw new RuntimeException(e);
        }
    }
}
