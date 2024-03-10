package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String email);
}
