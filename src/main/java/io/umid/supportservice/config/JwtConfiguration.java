package io.umid.supportservice.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfiguration {


    @Bean
    public JWSVerifier jwsVerifier(JwtProperties jwtProperties) throws JOSEException {
        return new MACVerifier(jwtProperties.getAccessTokenKey());
    }

    @Bean
    public JWSAlgorithm jwsAlgorithm() {
        return JWSAlgorithm.HS256;
    }

    @Bean
    public JWSSigner jwsSigner(JwtProperties jwtProperties) throws KeyLengthException {
        return new MACSigner(jwtProperties.getAccessTokenKey());
    }

    @Bean
    public JWEDecrypter jweDecrypter(JwtProperties jwtProperties) throws KeyLengthException {
        return new DirectDecrypter(jwtProperties.getRefreshTokenKey().getBytes(StandardCharsets.UTF_8));
    }

    @Bean
    public JWEEncrypter jweEncrypter(JwtProperties jwtProperties) throws KeyLengthException {
        return new DirectEncrypter(jwtProperties.getRefreshTokenKey().getBytes(StandardCharsets.UTF_8));
    }

    @Bean
    public JWEAlgorithm jweAlgorithm() {
        return JWEAlgorithm.DIR;
    }

    @Bean
    public EncryptionMethod encryptionMethod() {
        return EncryptionMethod.A128GCM;
    }

}
