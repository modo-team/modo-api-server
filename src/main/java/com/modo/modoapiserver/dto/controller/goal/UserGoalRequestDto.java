package com.modo.modoapiserver.dto.controller.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "사용자 목표 생성 요청 스키마")
@Getter
public class UserGoalRequestDto {
    @Schema(description = "목표 제목", required = true)
    private String title;

    @Schema(description = "목표 아이콘", required = true)
    private String icon;

    @Schema(description = "목표 난이도. 0: 쉬움, 1: 보통, 2: 어려움, (3: 안하면뒤진다. 추후 추가예정)", required = true)
    private Integer difficulty;

    @Schema(description = "팀 아이디", required = false)
    @JsonProperty("team_id")
    private Long teamId;

    @Schema(description = "카테고리 아이디", required = false)
    @JsonProperty("category_id")
    private Long categoryId;

    @Schema(description = "목표 인증 방법", required = false)
    @JsonProperty("verification_method")
    private String verificationMethod;

    @Schema(description = "목표 날짜", required = true)
    @JsonProperty("goal_datetime")
    private LocalDateTime goalDatetime;
}
