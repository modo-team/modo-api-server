package com.modo.modoapiserver.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저성별")
public enum Gender {
    @Schema(description = "여성")
    W("W"),
    @Schema(description = "남성")
    M("M");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
