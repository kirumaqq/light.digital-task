package io.umid.supportservice.security;

import java.util.Date;
import java.util.UUID;

public record RefreshToken(
        UUID id,
        String subject,
        Date issued,
        Date expiresAt

) {
}
