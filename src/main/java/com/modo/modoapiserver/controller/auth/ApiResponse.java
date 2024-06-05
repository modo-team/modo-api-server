package com.modo.modoapiserver.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ApiResponse {

    @JsonProperty
    private String msg;

    @JsonProperty
    private String code;

    public static ApiResponse of(String msg, String code) {
        return ApiResponse.builder()
                .msg(msg)
                .code(code)
                .build();
    }

}
