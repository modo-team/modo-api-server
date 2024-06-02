package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OauthLoginRequestDto {
    @JsonProperty("code")
    private String code;

    @JsonProperty("redirect_uri")
    private String requestUri;
}
