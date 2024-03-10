package io.umid.supportservice.controller;

import io.umid.supportservice.TestSupportServiceApplication;
import io.umid.supportservice.security.RefreshToken;
import io.umid.supportservice.security.RefreshTokenJweSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestSupportServiceApplication.class)
class JwtControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RefreshTokenJweSerializer refreshTokenSerializer;


    @Test
    @WithMockUser
    void getTokens_authenticatedUser_returnsJson() throws Exception {
        mockMvc.perform(get("/jwt/token"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @WithAnonymousUser
    void getTokens_unauthenticatedUser_401Unauthorized() throws Exception {
        mockMvc.perform(get("/jwt/token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void refresh_validRefreshToken_returnsJson() throws Exception {
        var date = Date.from(Instant.now().plus(10, ChronoUnit.DAYS));
        var refreshToken = new RefreshToken(UUID.randomUUID(), "user", new Date(), date);
        var serialized = refreshTokenSerializer.apply(refreshToken);
        var header = "Bearer %s".formatted(serialized);

        mockMvc.perform(get("/jwt/refresh")
                        .header("Authorization", header))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void refresh_invalidRefreshToken_401Unauthorized() throws Exception {

        mockMvc.perform(get("/jwt/refresh")
                        .header("Authorization", "hz"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void refresh_expiredRefreshToken_401Unauthorized() throws Exception {
        var date = Date.from(Instant.now().minus(10, ChronoUnit.DAYS));
        var refreshToken = new RefreshToken(UUID.randomUUID(), "user", new Date(), date);
        var serialized = refreshTokenSerializer.apply(refreshToken);
        var header = "Bearer %s".formatted(serialized);



        mockMvc.perform(get("/jwt/refresh")
                        .header("Authorization", header))
                .andExpect(status().isUnauthorized());
    }
}