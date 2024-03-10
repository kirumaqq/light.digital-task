package io.umid.supportservice.service;

import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.mapper.ApplicationMapper;
import io.umid.supportservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
