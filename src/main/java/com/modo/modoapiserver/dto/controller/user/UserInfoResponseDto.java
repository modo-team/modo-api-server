package com.modo.modoapiserver.dto.controller.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String nickname;
}
