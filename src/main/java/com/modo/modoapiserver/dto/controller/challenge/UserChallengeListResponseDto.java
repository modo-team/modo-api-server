package com.modo.modoapiserver.dto.controller.challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserChallengeListResponseDto {
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("user_challenge_list")
    private List<UserChallengeDto> userChallengeList;
}
