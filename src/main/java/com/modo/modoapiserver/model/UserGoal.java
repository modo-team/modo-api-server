package com.modo.modoapiserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_goal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class UserGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String icon;
    private Integer difficulty;
    private Long teamId;
    private Long categoryId;
    private String verificationMethod;
    private LocalDateTime goalDatetime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
