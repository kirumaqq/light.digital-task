package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getUserApplications(Pageable pageable, Integer userId);



}
