package com.modo.modoapiserver.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserGoalStatus {
    READY(0, "ready"),
    FAIL(1, "fail"),
    SUCCESS(2, "success");

    private final int value;
    private final String description;

    UserGoalStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public static UserGoalStatus fromValue(int value) {
        for (UserGoalStatus status : UserGoalStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    public String getDescription() {
        return description;
    }

    public static UserGoalStatus fromDescription(String description) {
        for (UserGoalStatus status : UserGoalStatus.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }

    @Override
    @JsonValue
    public String toString() {
        return description;
    }
}
