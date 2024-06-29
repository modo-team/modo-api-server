package com.modo.modoapiserver.dto.controller.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserGoalResponseDto {
    @JsonProperty("user_nickname")
    private String userNickname;
    private String status;
    private String title;
    private String difficulty;
}
