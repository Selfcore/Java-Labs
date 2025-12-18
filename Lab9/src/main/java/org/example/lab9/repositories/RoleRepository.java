package org.example.lab9.repositories;

import org.example.lab9.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findOneByName(String name);
    Optional<Role> findByName(String name);
}
