package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationRequest;
import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.exception.NotAllowedException;
import io.umid.supportservice.mapper.ApplicationMapper;
import io.umid.supportservice.model.ApplicationStatus;
import io.umid.supportservice.model.Roles;
import io.umid.supportservice.model.User;
import io.umid.supportservice.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    @Override
    public List<ApplicationResponse> getUserApplications(Pageable pageable, Integer userId) {
        log.debug("Searching applications of user with id: {}", userId);

        return applicationRepository.findAllByUserId(pageable, userId)
                .map(applicationMapper::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getAllApplications(Pageable pageable, String name, UserDetails userDetails) {
        log.debug("Searching applications filtered by name: {}", name);

        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        log.debug("User authorities: {}", authorities);

        var allowedStatuses = new ArrayList<ApplicationStatus>();

        if (authorities.contains(Roles.OPERATOR.withRolePrefix())) {
            allowedStatuses.add(ApplicationStatus.SENT);
        }
        if (authorities.contains(Roles.ADMIN.withRolePrefix())) {
            allowedStatuses.add(ApplicationStatus.DECLINED);
            allowedStatuses.add(ApplicationStatus.ACCEPTED);
        }

        log.debug("User is allowed to see applications with status: {}", allowedStatuses);

        return applicationRepository.findAllHavingStatusAndUsername(name, allowedStatuses, pageable)
                .map(applicationMapper::mapToResponse)
                .toList();
    }

    @Transactional
    @Override
    public ApplicationResponse editApplicationStatus(Integer appId, ApplicationStatus status) {
        log.debug("First, searching an application by id: {}", appId);

        var application = applicationRepository.findById(appId);

        log.debug("Application status: {}", application.getStatus());

        if (application.getStatus() != ApplicationStatus.SENT) {
            throw new NotAllowedException("Operator cannot edit applications with non-SENT status");
        }

        log.debug("Updating application status");
        applicationRepository.updateStatusById(appId, status);

        return applicationMapper.mapToResponse(application);
    }

    @Override
    public ApplicationResponse getApplicationById(Integer id) {
        var application = applicationRepository.findById(id);

        return applicationMapper.mapToResponse(application);
    }

    @Override
    public ApplicationResponse createApplication(ApplicationRequest applicationRequest, User user) {
        var application = applicationMapper.toApplication(applicationRequest);
        application.setUser(user);

        var saved = applicationRepository.save(application);

        return applicationMapper.mapToResponse(saved);
    }

    @Override
    public ApplicationResponse editApplication(ApplicationRequest applicationRequest, User user) {

        if (applicationRequest.status() != ApplicationStatus.DRAFT) {
            throw new NotAllowedException("Users cannot edit non-draft applications");
        }

        var application = applicationMapper.toApplication(applicationRequest);
        application.setUser(user);

        var updated = applicationRepository.save(application);

        return applicationMapper.mapToResponse(updated);
    }
}
