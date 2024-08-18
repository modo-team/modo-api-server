package com.modo.modoapiserver.dto.service.user;

import com.modo.modoapiserver.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
public class UserDto {
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String birth;
    private Gender gender;
    private String externalId;
    private String externalType;

    public UserDto() {
    }

    public UserDto(String email, String password, String nickname, Gender gender, String birth) {
        this.email = email;
        this.password = password;
        this.username = nickname;
        this.gender = gender;
        this.birth = birth;
    }

    public boolean hasValidPassword() {
        return StringUtils.hasText(this.password);
    }

}
