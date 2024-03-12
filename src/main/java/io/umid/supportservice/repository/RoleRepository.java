package io.umid.supportservice.repository;

import io.umid.supportservice.model.Role;
import io.umid.supportservice.model.Roles;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Integer> {

        Optional<Role> findByName(Roles name);
}
