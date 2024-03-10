package io.umid.supportservice.dto;

import io.umid.supportservice.model.ApplicationStatus;

import java.sql.Date;

public record ApplicationResponse(

        String name,
        String phoneNumber,
        ApplicationStatus status,
        String text,
        Date createdAt
) {
}
