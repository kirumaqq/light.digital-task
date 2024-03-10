package io.umid.supportservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {

    private final AuthenticationConverter authenticationConverter;

    private final AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> userDetailsService;

    @Override
    public void configure(HttpSecurity builder) {
        var authManager = builder.getSharedObject(AuthenticationManager.class);
        var jwtAuthFilter = new AuthenticationFilter(authManager, authenticationConverter);

        jwtAuthFilter.setSuccessHandler((request, response, authentication) -> {
        });
        jwtAuthFilter.setFailureHandler((request, response, exception) -> {
        });

        var authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(userDetailsService);


        builder
                .addFilterAfter(jwtAuthFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(authProvider);
    }


}
