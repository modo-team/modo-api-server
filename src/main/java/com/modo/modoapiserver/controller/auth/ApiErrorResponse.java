package com.modo.modoapiserver.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class ApiErrorResponse {

    @JsonProperty
    private String msg;

    @JsonProperty
    private String code;

    public static ApiErrorResponse of(String msg, String code) {
        return ApiErrorResponse.builder()
                .msg(msg)
                .code(code)
                .build();
    }

}
