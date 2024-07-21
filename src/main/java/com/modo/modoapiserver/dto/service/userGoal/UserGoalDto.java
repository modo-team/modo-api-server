package com.modo.modoapiserver.dto.service.userGoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modo.modoapiserver.enums.UserGoalDifficulty;
import com.modo.modoapiserver.enums.UserGoalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserGoalDto {
    private Long id;
    @JsonProperty("user_id")
    @Schema(description = "user_id, 요청시에는 무시함")
    private Long userId;
    private String title;
    @Schema(description = "목표 아이콘(이모티콘)")
    private String icon;
    @Schema(description = "목표 난이도. 0: 쉬움, 1: 보통, 2: 어려움, (3: 안하면뒤진다)")
    private UserGoalDifficulty difficulty;

    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("verification_method")
    private String verificationMethod;
    @JsonProperty("goal_datetime")
    private LocalDateTime goalDatetime;
    @JsonProperty("status")
    private UserGoalStatus status;
}
