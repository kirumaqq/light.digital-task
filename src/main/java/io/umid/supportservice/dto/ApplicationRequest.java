package io.umid.supportservice.dto;

import io.umid.supportservice.model.ApplicationStatus;

public record ApplicationRequest(

        String name,
        String text,
        String phoneNumber,
        ApplicationStatus status

) {
}
