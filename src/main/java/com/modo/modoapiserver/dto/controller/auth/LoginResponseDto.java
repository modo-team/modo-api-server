package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("code_verifier")
    private String codeVerifier;


    @Builder
    public LoginResponseDto(Long id, String accessToken, String codeVerifier) {
        this.id = id;
        this.accessToken = accessToken;
        this.codeVerifier = codeVerifier;
    }

}
