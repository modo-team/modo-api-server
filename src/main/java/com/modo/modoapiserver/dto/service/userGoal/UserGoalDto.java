package com.modo.modoapiserver.dto.service.userGoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserGoalDto {
    private Long id;
    @JsonProperty("user_id")
    private Long userId;
    private String title;
    private String icon;
    private Integer difficulty;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("verification_method")
    private String verificationMethod;
    @JsonProperty("goal_datetime")
    private LocalDateTime goalDatetime;
}
