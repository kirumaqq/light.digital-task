package io.umid.supportservice.service;

import feign.FeignException;
import io.umid.supportservice.client.PhoneCheckingClient;
import io.umid.supportservice.dto.PhoneCheckResponse;
import io.umid.supportservice.exception.ExternalServiceException;
import io.umid.supportservice.exception.PhoneNumberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExternalPhoneCheckingService implements PhoneCheckingService {

    private final PhoneCheckingClient phoneCheckingClient;
    private final String apiKey;
    private final String secretKey;

    public ExternalPhoneCheckingService(PhoneCheckingClient phoneCheckingClient,
                                        @Value("${dadata.key.api}") String apiKey,
                                        @Value("${dadata.key.secret}") String secretKey) {
        this.phoneCheckingClient = phoneCheckingClient;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    @Override
    public void checkPhoneNumber(String phoneNumber) {
        log.debug("Checking phone number: {}", phoneNumber);

        var authorization = "Token " + apiKey;
        var body = "[ \"%s\" ]".formatted(phoneNumber);

        log.debug("Sending request to external service");

        PhoneCheckResponse phoneCheck;
        try {
            phoneCheck = phoneCheckingClient.checkPhoneNumber(body, authorization, secretKey).get(0);
        } catch (FeignException e) {
            throw new ExternalServiceException(e.getMessage());
        }

        log.debug("Phone check: {}", phoneCheck);

        if (!phoneCheck.type().equals("Мобильный")) {
            throw new PhoneNumberException("Phone number must be mobile");
        }

        if (phoneCheck.phone() == null) {
            throw new PhoneNumberException("Invalid phone number");
        }
    }
}
