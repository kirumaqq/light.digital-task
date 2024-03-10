package io.umid.supportservice.repository;

import io.umid.supportservice.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ApplicationRepository extends Repository<Application, Integer> {


    Page<Application> findAllByUserId(Pageable pageable, Integer userId);


}
