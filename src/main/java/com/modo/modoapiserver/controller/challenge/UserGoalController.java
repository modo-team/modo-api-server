package com.modo.modoapiserver.controller.challenge;

import com.modo.modoapiserver.dto.controller.goal.UserGoalResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalListResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalRequestDto;
import com.modo.modoapiserver.dto.service.userGoal.UserGoalDto;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.repository.UserGoalRepository;
import com.modo.modoapiserver.security.CustomUserDetails;
import com.modo.modoapiserver.service.UserGoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserGoalController {
    @Autowired
    UserGoalService userGoalService;
    @Operation(summary = "내 목표 만들기", description = "목표를 생성합니다")
    @PostMapping("/goal")
    public ResponseEntity<UserGoal> saveUserGoal(@RequestBody UserGoalRequestDto data, @AuthenticationPrincipal CustomUserDetails userDetails){
        UserGoalDto userGoalDto = UserGoalDto.builder()
                .goalDatetime(data.getGoalDatetime())
                .title(data.getTitle())
                .icon(data.getIcon())
                .difficulty(data.getDifficulty())
                .teamId(data.getTeamId())
                .categoryId(data.getCategoryId())
                .verificationMethod(data.getVerificationMethod())
                .userId(userDetails.getIdentity())
                .build();
        UserGoal userGoal = userGoalService.saveUserGoal(userGoalDto);
        return ResponseEntity.ok(userGoal);
    }

    @Operation(summary = "내 주간 목표 목록 조회", description = "내가 이번주 목표를 조회합니다")
    @GetMapping("/my/challenges")
    public ResponseEntity<List<UserGoalListResponseDto>> getMyChallengeList() {
        List<UserGoalListResponseDto> userChallengeList = new ArrayList<>(2);

        UserGoalListResponseDto userChallengeListResponseDto = UserGoalListResponseDto.builder()
                .datetime("2021-10-10")
                .userChallengeList(new ArrayList<>(2))
                .build();

        userChallengeListResponseDto.getUserChallengeList().add(
                UserGoalResponseDto.builder()
                        .userNickname("두이")
                        .status("ready")
                        .title("운동하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeListResponseDto.getUserChallengeList().add(
                UserGoalResponseDto.builder()
                        .userNickname("두이")
                        .status("fail")
                        .title("공부하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeList.add(userChallengeListResponseDto);

        userChallengeListResponseDto = UserGoalListResponseDto.builder()
                .datetime("2021-10-11")
                .userChallengeList(new ArrayList<>(2))
                .build();


        userChallengeListResponseDto.getUserChallengeList().add(
                UserGoalResponseDto.builder()
                        .userNickname("두이")
                        .status("ready")
                        .title("운동하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeListResponseDto.getUserChallengeList().add(
                UserGoalResponseDto.builder()
                        .userNickname("두이")
                        .status("ready")
                        .title("공부하기")
                        .difficulty("difficult")
                        .build()
        );
        userChallengeList.add(userChallengeListResponseDto);
        return ResponseEntity.ok(userChallengeList);
    }
}
