package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getUserApplications(Pageable pageable, Integer userId);

    List<ApplicationResponse> getAllApplications(Pageable pageable, String name, UserDetails userDetails);

}
