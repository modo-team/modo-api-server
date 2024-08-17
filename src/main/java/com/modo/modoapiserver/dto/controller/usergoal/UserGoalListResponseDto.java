package com.modo.modoapiserver.dto.controller.usergoal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserGoalListResponseDto {
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("user_challenge_list")
    private List<UserGoalResponseDto> userChallengeList;
}
