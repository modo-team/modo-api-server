package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {

}
