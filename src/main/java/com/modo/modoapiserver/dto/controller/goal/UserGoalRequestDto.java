package com.modo.modoapiserver.dto.controller.goal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "사용자 목표 생성 요청 스키마")
@Getter
public class UserGoalRequestDto {
    @Schema(description = "목표 제목")
    private String title;

    @Schema(description = "목표 아이콘")
    private String icon;

    @Schema(description = "목표 난이도")
    private Integer difficulty;

    @Schema(description = "팀 아이디")
    private Long teamId;

    @Schema(description = "카테고리 아이디")
    private Long categoryId;

    @Schema(description = "목표 인증 방법")
    private String verificationMethod;

    @Schema(description = "목표 날짜")
    private LocalDateTime goalDatetime;
}
