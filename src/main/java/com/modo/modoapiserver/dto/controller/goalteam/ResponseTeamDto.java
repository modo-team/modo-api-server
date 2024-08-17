package com.modo.modoapiserver.dto.controller.goalteam;

import com.modo.modoapiserver.enums.TeamCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseTeamDto {
    private Integer id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String applymentQuestion;
    private Integer maxMemberNumber;
    private TeamCategory teamCategory;
}
