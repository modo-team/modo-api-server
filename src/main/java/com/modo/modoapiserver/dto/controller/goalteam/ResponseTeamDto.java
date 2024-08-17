package com.modo.modoapiserver.dto.controller.goalteam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.modo.modoapiserver.enums.TeamCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseTeamDto {
    private Integer id;
    private String name;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    private String description;
    @JsonProperty("applyment_question")
    private String applymentQuestion;
    @JsonProperty("max_member_number")
    private Integer maxMemberNumber;
    @JsonProperty("team_category")
    private TeamCategory teamCategory;
}
