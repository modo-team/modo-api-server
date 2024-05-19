package com.modo.modoapiserver.model;

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
    private int age;
    private String mobileNumber;
    private String externalId;

    public User(String email, String password, String username, int age, String mobileNumber, String externalId) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.externalId = externalId;
    }

    public User() {

    }
}
