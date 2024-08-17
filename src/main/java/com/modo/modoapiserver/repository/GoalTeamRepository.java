package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.GoalTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalTeamRepository extends JpaRepository<GoalTeam, Long> {
    public Optional<GoalTeam> findById(Long id);

    public List<GoalTeam> getTeamsOrderByNewest();

    public List<GoalTeam> getTeamsOrderByPopular();
}
