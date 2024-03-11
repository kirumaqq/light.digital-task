package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationRequest;
import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.dto.PageRequestDto;
import io.umid.supportservice.model.ApplicationStatus;
import io.umid.supportservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getUserApplications(PageRequestDto pageRequestDto, Integer userId);

    List<ApplicationResponse> getAllApplications(PageRequestDto pageRequestDto, String name, UserDetails userDetails);

    ApplicationResponse editApplicationStatus(Integer appId, ApplicationStatus status);

    ApplicationResponse getApplicationById(Integer id);

    ApplicationResponse createApplication(ApplicationRequest applicationRequest, User user);

    ApplicationResponse editApplication(ApplicationRequest applicationRequest, Integer appId, User user);

}
