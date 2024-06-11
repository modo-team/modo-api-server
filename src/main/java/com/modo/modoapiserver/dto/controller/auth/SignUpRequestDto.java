package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDto {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birth")
    private String birth;
}
