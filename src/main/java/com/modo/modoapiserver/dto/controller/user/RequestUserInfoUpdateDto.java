package com.modo.modoapiserver.dto.controller.user;

import com.modo.modoapiserver.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class RequestUserInfoUpdateDto {
    @NotBlank
    String userEmail;
    @NotBlank
    String userName;
    @NotBlank
    Gender userGender;
    @NotBlank
    LocalDate userBirth;
}
