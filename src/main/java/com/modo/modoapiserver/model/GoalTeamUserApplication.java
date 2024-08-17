package com.modo.modoapiserver.model;

import com.modo.modoapiserver.enums.GoalTeamUserApplicationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="goal_team_user_application")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GoalTeamUserApplication {
    /*
    create table modo.goal_team_user_application
    (
        id           bigint auto_increment
            primary key,
        goal_team_id bigint                             not null comment 'goal_team 테이블의 id',
        user_id      bigint                             not null comment 'user 테이블의 id',
        user_answer  text                               null comment '팀 참가신청 질문에 대한 유저의 응답',
        status       varchar(32)  default 0                 not null comment '0: 승인대기, 1: 참가승인, 2: 참가거절, 3: 참가취소',
        created_at   datetime default CURRENT_TIMESTAMP not null,
        updated_at   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    )
    comment '목표 팀 참가신청 테이블';
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long goalTeamId;
    private Long userId;
    private String userAnswer;
    @Enumerated(EnumType.STRING)
    private GoalTeamUserApplicationStatus status;
    private String createdAt;
    private String updatedAt;
}
