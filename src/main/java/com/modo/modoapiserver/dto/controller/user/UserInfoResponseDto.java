package com.modo.modoapiserver.dto.controller.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modo.modoapiserver.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth")
    private String birth;

    @JsonProperty("gender")
    private Gender gender;
}
