package io.umid.supportservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessTokenJwsSerializer implements Function<AccessToken, String> {

    private final JWSAlgorithm jwsAlgorithm;

    private final JWSSigner jwsSigner;

    @Override
    public String apply(AccessToken token) {
        log.debug("Serializing access token: {}", token);

        JWSHeader jwsHeader = new JWSHeader.Builder(jwsAlgorithm)
                .keyID(token.id().toString())
                .build();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id().toString())
                .subject(token.subject())
                .expirationTime(token.expiresAt())
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);

        try {
            signedJWT.sign(this.jwsSigner);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            log.warn("Internal JOSEException:", e);
            throw new RuntimeException(e);
        }
    }
}
