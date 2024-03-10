package io.umid.supportservice.controller;


import io.umid.supportservice.dto.ApplicationRequest;
import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.dto.PageRequestDto;
import io.umid.supportservice.model.ApplicationStatus;
import io.umid.supportservice.model.User;
import io.umid.supportservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/applications")
    public List<ApplicationResponse> getAllApplications(PageRequestDto pageReq,
                                                        @RequestParam(defaultValue = "") String name,
                                                        @AuthenticationPrincipal User user) {
        log.info("User, {}, requested all applications filtered by name: {}", user.getUsername(), name);

        Pageable pageable = PageRequest
                .of(pageReq.getPage(), pageReq.getSize(), pageReq.getDirection(), pageReq.getSortBy());
        return applicationService.getAllApplications(pageable, name, user);
    }

    @PatchMapping("/application/{id}")
    public ApplicationResponse editStatus(@PathVariable Integer id, ApplicationStatus status) {
        log.info("Changing status of an application with id {} to {}", id, status);

        return applicationService.editApplicationStatus(id, status);
    }

    @GetMapping("/application/{id}")
    public ApplicationResponse getById(@PathVariable Integer id) {
        log.info("Searching for an application by its id: {}", id);

        return applicationService.getApplicationById(id);
    }

    @PostMapping("/applications")
    public ApplicationResponse create(ApplicationRequest applicationRequest,
                                      @AuthenticationPrincipal User user) {
        log.info("User {} is creating new application: {}", user.getUsername(), applicationRequest);

        return applicationService.createApplication(applicationRequest, user);
    }

    @PutMapping("/applications")
    public ApplicationResponse edit(ApplicationRequest applicationRequest,
                                    @AuthenticationPrincipal User user) {
        log.info("User {} is editing application: {}", user.getUsername(), applicationRequest);

        return applicationService.editApplication(applicationRequest, user);
    }

}
