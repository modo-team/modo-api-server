package com.modo.modoapiserver.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "목표의 난이도")
public enum ChallengeDifficulty {
    @Schema(description = "쉬움")
    EASY,
    @Schema(description = "보통")
    MODERATE,
    @Schema(description = "어려움")
    DIFFICULT
}
