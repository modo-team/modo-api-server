package com.modo.modoapiserver.controller.goalteam;

import com.modo.modoapiserver.dto.controller.goalteam.RequestCreateTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.RequestPatchTeamDto;
import com.modo.modoapiserver.dto.controller.goalteam.ResponseTeamDto;
import com.modo.modoapiserver.enums.OrderValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/teams")
public class GoalTeamController {
    /// # CRUD
    /// 팀 생성 API
    @PostMapping()
    public ResponseTeamDto createTeam(@RequestBody RequestCreateTeamDto requestCreateTeamDto){
        return ResponseTeamDto.
                builder().
                description("test").name("test").build();
    }

    /// 팀 제거 API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Integer id){
        return ResponseEntity.ok().build();
    }

    /// 팀 수정 API
    @PatchMapping("/{id}")
    public ResponseTeamDto patchTeam(@PathVariable("id") Integer id, @RequestBody RequestPatchTeamDto requestPatchTeamDto){
        return ResponseTeamDto.
                builder().
                description("test").name("test").build();
    }


    /// 팀 상세 조회 API
    @GetMapping("/{id}")
    public ResponseTeamDto patchTeam(@PathVariable("id") Integer id){
        return ResponseTeamDto.builder().id(id).description("team").build();
    }

    /// # 리스트 조회
    /// 팀 목록 조회 API - 최신순
    /// 팀 목록 조회 API - 인기순
    @GetMapping()
    public List<ResponseTeamDto> getTeams(@RequestParam("order")OrderValue orderValue){
        return new ArrayList<ResponseTeamDto>(2);
    }

}
