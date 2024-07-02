package com.modo.modoapiserver.controller.goal;

import com.modo.modoapiserver.dto.controller.goal.UserGoalResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalListResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalRequestDto;
import com.modo.modoapiserver.dto.service.userGoal.UserGoalDto;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.security.CustomUserDetails;
import com.modo.modoapiserver.service.UserGoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserGoalController {
    @Autowired
    UserGoalService userGoalService;
    @Operation(summary = "목표 생성하기", description = "목표를 생성합니다")
    @PostMapping("/goal")
    public ResponseEntity<UserGoalDto> saveUserGoal(@RequestBody UserGoalRequestDto data, @AuthenticationPrincipal CustomUserDetails userDetails){
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

        UserGoalDto newUserGoalDto = UserGoalDto.builder()
                .id(userGoal.getId())
                .goalDatetime(userGoal.getGoalDatetime())
                .title(userGoal.getTitle())
                .icon(userGoal.getIcon())
                .difficulty(userGoal.getDifficulty())
                .teamId(userGoal.getTeamId())
                .categoryId(userGoal.getCategoryId())
                .verificationMethod(userGoal.getVerificationMethod())
                .userId(userGoal.getUserId())
                .build();

        return ResponseEntity.ok(newUserGoalDto);
    }

    @Operation(summary = "목표 상세 가져오기", description = "목표 상세를 가져옵니다")
    @GetMapping("/goal/{id}")
    public ResponseEntity<UserGoalDto> getUserGoal(@PathVariable("id") Long id){
        // TODO: jwt 토큰에서 사용자 정보를 가져와서 사용자의 목표인지 확인하는 로직 추가
        UserGoal userGoal = userGoalService.getUserGoal(id);
        UserGoalDto userGoalDto = UserGoalDto.builder()
                .id(userGoal.getId())
                .goalDatetime(userGoal.getGoalDatetime())
                .title(userGoal.getTitle())
                .icon(userGoal.getIcon())
                .difficulty(userGoal.getDifficulty())
                .teamId(userGoal.getTeamId())
                .categoryId(userGoal.getCategoryId())
                .verificationMethod(userGoal.getVerificationMethod())
                .userId(userGoal.getUserId())
                .build();

        return ResponseEntity.ok(userGoalDto);
    }

    @Operation(summary = "목표 지우기", description = "목표를 삭제합니다.")
    @DeleteMapping("/goal/{id}")
    public ResponseEntity<?> deleteUserGoal(@PathVariable("id") Long id){
        // TODO: jwt 토큰에서 사용자 정보를 가져와서 사용자의 목표인지 확인하는 로직 추가
        userGoalService.deleteUserGoal(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "목표 업데이트", description = "목표를 업데이트합니다.")
    @PatchMapping("/goal/{id}")
    public ResponseEntity<?> patchUserGoal(@PathVariable("id") Long id, @RequestBody UserGoalRequestDto userGoalRequestDto){
        // TODO: jwt 토큰에서 사용자 정보를 가져와서 사용자의 목표인지 확인하는 로직 추가
        UserGoalDto userGoalDto = UserGoalDto.builder()
                .goalDatetime(userGoalRequestDto.getGoalDatetime())
                .title(userGoalRequestDto.getTitle())
                .icon(userGoalRequestDto.getIcon())
                .difficulty(userGoalRequestDto.getDifficulty())
                .teamId(userGoalRequestDto.getTeamId())
                .categoryId(userGoalRequestDto.getCategoryId())
                .verificationMethod(userGoalRequestDto.getVerificationMethod())
                .build();
        userGoalService.updateUserGoal(id, userGoalDto);
        return ResponseEntity.ok().build();
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
