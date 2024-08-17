package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "카카오 로그인/회원가입 요청 스키마")
@Getter
public class RequestOauthLoginDto {
    @Schema(description = "auhtorization code")
    @JsonProperty("code")
    private String code;

    @Schema(description = "web 에서 동작시 입력")
    @JsonProperty("redirect_uri")
    private String requestUri;

    @Schema(description = "OAuth 2.1 동작시 입력")
    @JsonProperty("code_verifier")
    private String codeVerifier;

    @Schema(description = "code 전달 없이 access token 바로 입력시 입력")
    @JsonProperty("access_token")
    private String accessToken;
}
