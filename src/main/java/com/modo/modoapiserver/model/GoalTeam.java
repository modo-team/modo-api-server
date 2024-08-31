package com.modo.modoapiserver.model;

import com.modo.modoapiserver.enums.TeamCategory;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="goal_team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class GoalTeam {
    /*
    create table modo.goal_team
    (
        id                 bigint auto_increment
            primary key,
        name               varchar(64)                          not null comment '팀 이름',
        start_date         date                                 not null comment '팀 운영 시작일자',
        end_date           date                                 not null comment '팀 운영 종료일자',
        description        text                                 not null comment '팀 소개글',
        applyment_question text                                 not null comment '팀 참가신청 질문',
        max_member_number  int        default 8                 not null comment '팀 구성원 최대인원',
        team_category      int                                  not null comment '팀 카테고리 id',
        is_deleted         tinyint(1) default 0                 not null,
        created_at         datetime   default CURRENT_TIMESTAMP not null,
        updated_at         datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
    )
    comment '목표 팀 테이블';
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String applymentQuestion;
    private Integer maxMemberNumber;
    @Enumerated(EnumType.STRING)
    private TeamCategory teamCategory;
    @Builder.Default
    private Boolean isDeleted = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
