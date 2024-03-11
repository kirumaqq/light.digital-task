package io.umid.supportservice.dto;

public record PhoneCheckResponse(

        String type,
        String phone,
        String countryCode,
        String cityCode

) {
}
