package com.modo.modoapiserver.dto.service.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String birth;
    private String gender;
    private String externalId;
    private String externalType;

    public UserDto() {
    }

    public UserDto(String email, String password, String nickname, String gender, String birth) {
        this.email = email;
        this.password = password;
        this.username = nickname;
        this.gender = gender;
        this.birth = birth;
    }

}
