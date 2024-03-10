package io.umid.supportservice.controller;


import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.dto.PageRequestDto;
import io.umid.supportservice.model.User;
import io.umid.supportservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ApplicationsController {

    private final ApplicationService applicationService;


    @GetMapping("/user/applications")
    public List<ApplicationResponse> getUserApplications(PageRequestDto pageReq,
                                                         @AuthenticationPrincipal User user) {
        log.info("User, {}, requested applications. Page request: {}", user.getUsername(), pageReq);

        Pageable pageable = PageRequest
                .of(pageReq.getPage(), pageReq.getSize(), pageReq.getDirection(), pageReq.getSortBy());
        return applicationService.getUserApplications(pageable, user.getId());
    }



}
