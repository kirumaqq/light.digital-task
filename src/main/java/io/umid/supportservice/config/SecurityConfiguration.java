package io.umid.supportservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static io.umid.supportservice.model.Roles.*;
import static org.springframework.http.HttpMethod.*;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final JwtConfigurer jwtConfigurer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.with(jwtConfigurer, Customizer.withDefaults());

        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/jwt/token").authenticated()
                        .requestMatchers("/jwt/refresh").permitAll()
                        .requestMatchers(POST, "/applications").hasRole(USER.name())
                        .requestMatchers(PUT, "/application/*").hasRole(USER.name())
                        .requestMatchers(GET, "/user/applications").hasRole(USER.name())
                        .requestMatchers(GET, "/application/*").hasRole(OPERATOR.name())
                        .requestMatchers(PATCH, "/application/*").hasRole(OPERATOR.name())
                        .requestMatchers(GET, "/applications")
                        .hasAnyRole(OPERATOR.name(), ADMIN.name())
                        .requestMatchers(PATCH, "/user/*").hasRole(ADMIN.name())
                        .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }


}
