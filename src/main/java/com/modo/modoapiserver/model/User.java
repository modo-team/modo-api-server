package com.modo.modoapiserver.model;

import com.modo.modoapiserver.dto.service.user.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String birth;
    private String gender;
    private String mobileNumber;
    private String externalId;
    private String externalType;

    public User(UserDto userDto) {
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.username = userDto.getUsername();
        this.birth = userDto.getBirth();
        this.gender = userDto.getGender();
        this.mobileNumber = userDto.getMobileNumber();
        this.externalId = userDto.getExternalId();
        this.externalType = userDto.getExternalType();
    }
}
