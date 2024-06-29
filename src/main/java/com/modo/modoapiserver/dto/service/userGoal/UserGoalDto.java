package com.modo.modoapiserver.dto.service.userGoal;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserGoalDto {
    private Long userId;
    private String title;
    private String icon;
    private Integer difficulty;
    private Long teamId;
    private Long categoryId;
    private String verificationMethod;
    private LocalDateTime goalDatetime;
}
