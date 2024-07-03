package com.modo.modoapiserver.dto.controller.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modo.modoapiserver.enums.UserGoalDifficulty;
import com.modo.modoapiserver.enums.UserGoalStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserGoalResponseDto {
    private String icon;
    @JsonProperty("user_nickname")
    private String userNickname;
    private UserGoalStatus status;
    private String title;
    private UserGoalDifficulty difficulty;
}
