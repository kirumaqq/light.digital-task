package io.umid.supportservice.security;

import java.util.Date;
import java.util.UUID;

public record AccessToken(
        UUID id,
        String subject,
        Date expiresAt

) {
}
