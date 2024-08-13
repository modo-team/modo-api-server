package com.modo.modoapiserver.enums;

import lombok.Getter;

@Getter
public enum TeamCategory {
    /// 종합, 공부, 운동, 독서, 외국어, 루틴, 건강, 취미, 미라클모닝, 사이드프로젝트
    COMPREHENSIVE(0, "comprehensive"),
    STUDY(1, "study"),
    EXERCISE(2, "exercise"),
    READING(3, "reading"),
    LANGUAGE(4, "language"),
    ROUTINE(5, "routine"),
    HEALTH(6, "health"),
    HOBBY(7, "hobby"),
    MIRACLE_MORNING(8, "miracle_morning"),
    SIDE_PROJECT(9, "side_project");

    private final int value;
    private final String description;

    TeamCategory(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
