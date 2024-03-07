package io.umid.supportservice.repository;

import io.umid.supportservice.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {


    Optional<User> findByUsername(String username);

}
