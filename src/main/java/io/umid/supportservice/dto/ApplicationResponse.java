package io.umid.supportservice.dto;

import io.umid.supportservice.model.ApplicationStatus;

public record ApplicationResponse(

        String name,
        String phoneNumber,
        ApplicationStatus status,
        String text
) {
}
