package com.modo.modoapiserver.repository;

import com.modo.modoapiserver.model.GoalTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GoalTeamRepository extends JpaRepository<GoalTeam, Long> {
    public Optional<GoalTeam> findById(Long id);

    @Query("SELECT gt FROM GoalTeam gt ORDER BY gt.createdAt DESC")
    public List<GoalTeam> getTeamsOrderByNewest();

    /// GoalTeam 과 GoalTeamsUserLikesAssociation 을 조인해서 GROUP BY 를 한 다음 총 좋아요가 많은 팀 순으로 정렬
    @Query("SELECT gt FROM GoalTeam gt JOIN GoalTeamsUserLikesAssociation gtl ON gt.id = gtl.goalTeamId GROUP BY gt.id ORDER BY COUNT(gtl.userId) DESC")
    public List<GoalTeam> getTeamsOrderByPopular();
}
