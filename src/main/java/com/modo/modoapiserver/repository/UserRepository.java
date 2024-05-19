package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByExternalId(String externalId);
}
