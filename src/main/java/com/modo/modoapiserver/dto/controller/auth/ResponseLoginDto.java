package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseLoginDto {
    @JsonProperty("id")
    @Schema(description = "유저 id")
    private Long id;

    @JsonProperty("access_token")
    @Schema(description = "jwt 액세스 토큰")
    private String accessToken;

    @JsonProperty("code_verifier")
    @Schema(description = "OAuth 2.1 동작시 응답")
    private String codeVerifier;


    @Builder
    public ResponseLoginDto(Long id, String accessToken, String codeVerifier) {
        this.id = id;
        this.accessToken = accessToken;
        this.codeVerifier = codeVerifier;
    }

}
