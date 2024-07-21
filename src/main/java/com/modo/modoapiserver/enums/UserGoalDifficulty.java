package com.modo.modoapiserver.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "목표의 난이도")
public enum UserGoalDifficulty {
    @Schema(description = "쉬움")
    EASY(0, "easy"),
    @Schema(description = "보통")
    MODERATE(1, "moderate"),
    @Schema(description = "어려움")
    DIFFICULT(2, "difficult"),
    @Schema(description = "안하면 죽는다")
    COMPLETE_OR_DEATH(3, "complete_or_death");

    private final int value;
    private final String description;

    UserGoalDifficulty(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public static UserGoalDifficulty fromValue(int value) {
        for (UserGoalDifficulty status : UserGoalDifficulty.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    public String getDescription() {
        return description;
    }

    @Override
    @JsonValue
    public String toString() {
        return description;
    }
}
