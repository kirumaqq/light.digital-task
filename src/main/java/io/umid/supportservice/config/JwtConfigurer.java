package io.umid.supportservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {

    private final AuthenticationConverter authenticationConverter;

    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity builder) {

        var jwtAuthFilter = new AuthenticationFilter(authenticationManager, authenticationConverter);

        jwtAuthFilter.setSuccessHandler((request, response, authentication) -> {
        });

        jwtAuthFilter.setFailureHandler(
                new AuthenticationEntryPointFailureHandler(new Http403ForbiddenEntryPoint()));

        builder.addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class);
    }


}
