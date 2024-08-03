package com.modo.modoapiserver.controller.goalTeam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/// 목표 팀 테이블
/// 목표 팀 - 유저 연관 테이블
/// 목표 - 목표 팀 연관 테이블
/// 목표 팀 카테고리 Enum
/// 목표 팀 좋아요 테이블


@RestController
@RequestMapping("/api/teams")
public class GoalTeamController {
    /// # CRUD
    /// 팀 생성 API
    /// 팀 제거 API
    /// 팀 수정 API
    /// 팀 상세 조회 API

    /// # 리스트 조회
    /// 팀 목록 조회 API - 최신순
    /// 팀 목록 조회 API - 인기순

    /// # 공통 상호작용
    /// 팀 좋아요 API

    /// # 참가자 상호작용
    /// 팀 나가기 API
    /// 팀 참가신청 API
    /// 팀 참가신청 취소 API

    /// # 팀장 상호작용
    /// 팀 참가신청 수락 API
    /// 팀 참가신청 거절 API

}
