package com.modo.modoapiserver.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="goal_teams_user_likes_association")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class GoalTeamsUserLikesAssociation {
    /*
    create table modo.goal_teams_user_likes_association
    (
        id           bigint auto_increment
            primary key,
        goal_team_id bigint                               not null comment 'goal_team 테이블의 id',
        user_id      bigint                               not null comment 'user 테이블의 id',
        is_deleted   tinyint(1) default 0                 not null,
        created_at   datetime   default CURRENT_TIMESTAMP not null,
        updated_at   datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    )
    comment '목표 팀에대한 유저들의 좋아요 테이블';
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long goalTeamId;
    private Long userId;
    private Boolean isDeleted;
    private String createdAt;
    private String updatedAt;
}
