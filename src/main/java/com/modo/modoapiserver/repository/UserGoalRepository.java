package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    @Query("SELECT ug FROM UserGoal ug JOIN ug.user u WHERE ug.user.id = :userId AND ug.goalDatetime BETWEEN :start AND :end")
    List<UserGoal> findByUserIdAndGoalDatetimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
