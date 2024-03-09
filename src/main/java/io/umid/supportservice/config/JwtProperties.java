package io.umid.supportservice.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String accessTokenKey;

    private final String refreshTokenKey;

    private final Duration accessTokenTtl;

    private final Duration refreshTokenTtl;


}

