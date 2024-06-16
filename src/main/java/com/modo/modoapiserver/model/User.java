package com.modo.modoapiserver.model;

import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String birth;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;
    private String mobileNumber;
    private String externalId;
    private String externalType;

    public boolean isEmpty() {
        return this.id == null;
    }

    public static User empty() {
        return User.builder().build();
    }

}
