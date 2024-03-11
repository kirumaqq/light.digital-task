package io.umid.supportservice.client;

import io.umid.supportservice.dto.PhoneCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "phone-check")
public interface PhoneCheckingClient {


    @PostMapping(path = "/clean/phone",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PhoneCheckResponse> checkPhoneNumber(@RequestBody String body,
                          @RequestHeader(HttpHeaders.AUTHORIZATION) String apiKey,
                          @RequestHeader("X-Secret") String secretKey);
}
