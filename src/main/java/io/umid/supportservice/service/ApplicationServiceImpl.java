package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.mapper.ApplicationMapper;
import io.umid.supportservice.model.ApplicationStatus;
import io.umid.supportservice.model.Roles;
import io.umid.supportservice.repository.ApplicationRepository;
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

        if (authorities.contains(Roles.OPERATOR.name())) {
            allowedStatuses.add(ApplicationStatus.SENT);
        }
        if (authorities.contains(Roles.ADMIN.name())) {
            allowedStatuses.add(ApplicationStatus.DECLINED);
            allowedStatuses.add(ApplicationStatus.ACCEPTED);
        }

        log.debug("User is allowed to see applications with status: {}", allowedStatuses);

        return applicationRepository.findAllByNameContainingAndStatusIn(name, allowedStatuses, pageable)
                .map(applicationMapper::mapToResponse)
                .toList();
    }

    @Override
    public ApplicationResponse editApplicationStatus(Integer appId, ApplicationStatus status) {
        var application = applicationRepository.updateStatusById(appId, status);

        return applicationMapper.mapToResponse(application);
    }
}
