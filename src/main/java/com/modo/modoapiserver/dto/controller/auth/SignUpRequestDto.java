package com.modo.modoapiserver.dto.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modo.modoapiserver.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "회원가입 요청 스키마")
@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDto {
    @Schema(description = "유저이메일")
    @JsonProperty("email")
    private String email;

    @Schema(description = "유저비밀번호")
    @JsonProperty("password")
    private String password;

    @Schema(description = "유저닉네임")
    @JsonProperty("nickname")
    private String nickname;

    @Schema(description = "유저성별")
    @JsonProperty("gender")
    private Gender gender;

    @Schema(description = "생년월일", example = "19990101")
    @JsonProperty("birth")
    private String birth;
}
