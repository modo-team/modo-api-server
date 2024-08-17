package com.modo.modoapiserver.dto.service.goalteam;

import com.modo.modoapiserver.enums.TeamCategory;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GoalTeamDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String applymentQuestion;
    private Integer maxMemberNumber;
    private TeamCategory teamCategory;

}
