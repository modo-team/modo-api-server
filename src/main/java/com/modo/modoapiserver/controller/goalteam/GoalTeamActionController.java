package com.modo.modoapiserver.controller.goalteam;

import com.modo.modoapiserver.dto.controller.goalteam.RequestCreateTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.RequestPatchTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.ResponseTeamDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/// 목표 팀 테이블
/// 목표 팀 - 유저 연관 테이블
/// 목표 - 목표 팀 연관 테이블
/// 목표 팀 카테고리 Enum
/// 목표 팀 좋아요 테이블


@RestController
@RequestMapping("/api/teams")
public class GoalTeamActionController {
    /// # 공통 상호작용
    /// 팀 좋아요 API
    @PostMapping("/{teamId}/like")
    public ResponseEntity<?> likeTeam(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok().build();
    }

    /// 팀 좋아요 취소 API
    @DeleteMapping("/{teamId}/like")
    public ResponseEntity<?> unlikeTeam(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok().build();
    }

    /// # 참가자 상호작용
    /// 팀 나가기 API
    @DeleteMapping("/{teamId}/member")
    public ResponseEntity<?> leaveTeam(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok().build();
    }

    /// 팀 참가신청 API
    @PostMapping("/{teamId}/apply")
    public ResponseEntity<?> applyTeam(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok().build();
    }

    /// 팀 참가신청 취소 API
    @DeleteMapping("/{teamId}/apply")
    public ResponseEntity<?> cancelApplyTeam(@PathVariable("teamId") Long teamId) {
        return ResponseEntity.ok().build();
    }

    /// # 팀장 상호작용
    /// 팀 참가신청 수락 API
    @PostMapping("/{teamId}/apply/{userId}/accept")
    public ResponseEntity<?> acceptApplyTeam(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return ResponseEntity.ok().build();
    }

    /// 팀 참가신청 거절 API
    @DeleteMapping("/{teamId}/apply/{userId}/reject")
    public ResponseEntity<?> rejectApplyTeam(@PathVariable("teamId") Long teamId, @PathVariable("userId") Long userId) {
        return ResponseEntity.ok().build();
    }
}
