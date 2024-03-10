package io.umid.supportservice.repository;

import io.umid.supportservice.model.Application;
import io.umid.supportservice.model.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface ApplicationRepository extends Repository<Application, Integer> {


    Page<Application> findAllByUserId(Pageable pageable, Integer userId);

    Page<Application> findAllByNameContainingAndStatusIn(String name, Collection<ApplicationStatus> status, Pageable pageable);

    @Modifying
    @Query("""
            update Application a
            set a.status = :status
            where a.id = :id2
            """)
    Application updateStatusById(Integer id, ApplicationStatus status);

    Application findById(Integer id);

    void save(Application application);

}
