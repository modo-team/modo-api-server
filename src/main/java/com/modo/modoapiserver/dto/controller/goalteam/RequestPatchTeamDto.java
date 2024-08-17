package com.modo.modoapiserver.dto.controller.goalteam;

import com.modo.modoapiserver.enums.TeamCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RequestPatchTeamDto {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String applymentQuestion;
    private Integer maxMemberNumber;
    private TeamCategory teamCategory;
}
