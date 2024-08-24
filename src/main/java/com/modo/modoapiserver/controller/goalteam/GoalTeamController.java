package com.modo.modoapiserver.controller.goalteam;

import com.modo.modoapiserver.dto.controller.goalteam.RequestCreateTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.RequestPatchTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.ResponseTeamDto;
import com.modo.modoapiserver.dto.service.goalteam.GoalTeamDto;
import com.modo.modoapiserver.enums.OrderValue;
import com.modo.modoapiserver.security.CustomUserDetails;
import com.modo.modoapiserver.service.GoalTeamService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/teams")
public class GoalTeamController {
    @Autowired
    private GoalTeamService goalTeamService;

    private ResponseTeamDto convertToResponseTeamDto(GoalTeamDto goalTeamDto){
        return ResponseTeamDto.builder().
                id(goalTeamDto.getId()).
                teamCategory(goalTeamDto.getTeamCategory()).
                applymentQuestion(goalTeamDto.getApplymentQuestion()).
                description(goalTeamDto.getDescription()).
                startDate(goalTeamDto.getStartDate()).
                endDate(goalTeamDto.getEndDate()).
                maxMemberNumber(goalTeamDto.getMaxMemberNumber()).
                name(goalTeamDto.getName()).
                build();
    }

    /// 팀 생성 API
    @Operation(summary = "팀 생성 API",
            description = "팀을 생성합니다.")
    @PostMapping()
    public ResponseTeamDto createTeam(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody RequestCreateTeamDto requestCreateTeamDto
    ){
        GoalTeamDto goalTeamDto = GoalTeamDto.builder().
                description(requestCreateTeamDto.getDescription()).
                endDate(requestCreateTeamDto.getEndDate()).
                maxMemberNumber(requestCreateTeamDto.getMaxMemberNumber()).
                name(requestCreateTeamDto.getName()).
                startDate(requestCreateTeamDto.getStartDate()).build();

        GoalTeamDto createdGoalTeamDto = goalTeamService.createTeam(goalTeamDto);
        return convertToResponseTeamDto(createdGoalTeamDto);
    }

    /// 팀 제거 API
    @Operation(summary = "팀 제거 API",
            description = "팀을 제거합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id){
        goalTeamService.deleteTeamById(id);
        return ResponseEntity.ok().build();
    }

    /// 팀 수정 API
    @Operation(summary = "팀 수정 API",
            description = "팀을 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseTeamDto patchTeam(@PathVariable("id") Long id, @RequestBody RequestPatchTeamDto requestPatchTeamDto){
        GoalTeamDto goalTeamDto = this.goalTeamService.getTeamById(id);

        if(requestPatchTeamDto.getDescription() != null){
            goalTeamDto.setDescription(requestPatchTeamDto.getDescription());
        }
        if(requestPatchTeamDto.getEndDate() != null){
            goalTeamDto.setEndDate(requestPatchTeamDto.getEndDate());
        }
        if(requestPatchTeamDto.getMaxMemberNumber() != null){
            goalTeamDto.setMaxMemberNumber(requestPatchTeamDto.getMaxMemberNumber());
        }
        if(requestPatchTeamDto.getName() != null){
            goalTeamDto.setName(requestPatchTeamDto.getName());
        }
        if(requestPatchTeamDto.getStartDate() != null){
            goalTeamDto.setStartDate(requestPatchTeamDto.getStartDate());
        }
        if(requestPatchTeamDto.getTeamCategory() != null){
            goalTeamDto.setTeamCategory(requestPatchTeamDto.getTeamCategory());
        }
        if(requestPatchTeamDto.getApplymentQuestion() != null){
            goalTeamDto.setApplymentQuestion(requestPatchTeamDto.getApplymentQuestion());
        }

        GoalTeamDto updatedGoalTeamdto = goalTeamService.updateTeam(goalTeamDto);

        return convertToResponseTeamDto(updatedGoalTeamdto);
    }


    /// 팀 상세 조회 API
    @Operation(summary = "팀 상세 조회 API",
            description = "팀 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseTeamDto getTeam(@PathVariable("id") Long id){
        GoalTeamDto goalTeamDto = goalTeamService.getTeamById(id);
        return convertToResponseTeamDto(goalTeamDto);
    }

    /// # 리스트 조회
    /// 팀 목록 조회 API - 최신순
    /// 팀 목록 조회 API - 인기순
    @Operation(summary = "팀 목록 조회 API",
            description = "팀 목록을 조회합니다.")
    @GetMapping()
    public List<ResponseTeamDto> getTeams(@RequestParam("order")OrderValue orderValue){
        List<GoalTeamDto> goalTeamDtos = new ArrayList<>();
        if(orderValue == OrderValue.NEWEST){
            goalTeamDtos = goalTeamService.getTeamsByNewest();
        }else if(orderValue == OrderValue.POPULAR){
            goalTeamDtos = goalTeamService.getTeamsByPopular();
        }

        List<ResponseTeamDto> responseTeamDtos = new ArrayList<>();
        for(GoalTeamDto goalTeamDto : goalTeamDtos){
            responseTeamDtos.add(convertToResponseTeamDto(goalTeamDto));
        }

        return responseTeamDtos;
    }

}
