package com.modo.modoapiserver.controller.goal;

import com.modo.modoapiserver.dto.controller.goal.CreateUserGoalRequestDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalListResponseDto;
import com.modo.modoapiserver.dto.controller.goal.UserGoalRequestDto;
import com.modo.modoapiserver.dto.service.userGoal.UserGoalDto;
import com.modo.modoapiserver.enums.UserGoalDifficulty;
import com.modo.modoapiserver.enums.UserGoalStatus;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.security.CustomUserDetails;
import com.modo.modoapiserver.service.UserGoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/user")
public class UserGoalController {
    @Autowired
    UserGoalService userGoalService;
    @Operation(summary = "목표 생성하기", description = "목표를 생성합니다. 날짜가 여러개 들어오면 다른 날짜의 같은 목표가 다중으로 생성됩니다.")
    @PostMapping("/goals")
    public ResponseEntity<List<UserGoalDto>> saveUserGoal(@RequestBody CreateUserGoalRequestDto data, @AuthenticationPrincipal CustomUserDetails userDetails){
        List<UserGoal> userGoals = new ArrayList<>();
        for (LocalDateTime goalDatetime : data.getGoalDatetimeList()) {
            UserGoalDto userGoalDto = UserGoalDto.builder()
                    .goalDatetime(goalDatetime)
                    .title(data.getTitle())
                    .icon(data.getIcon())
                    .difficulty(data.getDifficulty())
                    .teamId(data.getTeamId())
                    .categoryId(data.getCategoryId())
                    .verificationMethod(data.getVerificationMethod())
                    .userId(userDetails.getIdentity())
                    .status(UserGoalStatus.READY)
                    .build();
            userGoals.add(userGoalService.saveUserGoal(userGoalDto));
        }

        List<UserGoalDto> userGoalDtoList = userGoals.stream()
                .map(userGoal -> UserGoalDto.builder()
                        .id(userGoal.getId())
                        .goalDatetime(userGoal.getGoalDatetime())
                        .title(userGoal.getTitle())
                        .icon(userGoal.getIcon())
                        .difficulty(userGoal.getDifficulty())
                        .teamId(userGoal.getTeamId())
                        .categoryId(userGoal.getCategoryId())
                        .verificationMethod(userGoal.getVerificationMethod())
                        .userId(userGoal.getUserId())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userGoalDtoList);
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
    public ResponseEntity<List<UserGoalListResponseDto>> getMyChallengeList(@AuthenticationPrincipal CustomUserDetails userDetails) {
        LocalDateTime startOfWeek = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfWeek = startOfWeek.plusDays(7).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        List<UserGoal> userGoalsThisWeek = this.userGoalService.getUserGoalsBetween(userDetails.getIdentity(), startOfWeek, endOfWeek);

        // 가져온 userGoalsThisWeek 를 년/월/일 을 기준으로 Grouping 해서, UserGoalListResponseDto 를 만들어서 반환한다.
        Map<LocalDate, List<UserGoalResponseDto>> groupedGoals = userGoalsThisWeek.stream()
                .collect(Collectors.groupingBy(
                        userGoal -> userGoal.getGoalDatetime().toLocalDate(),
                        Collectors.mapping(this::convertToDto, Collectors.toList())
                ));

        List<UserGoalListResponseDto> response = groupedGoals.entrySet().stream()
                .map(entry -> UserGoalListResponseDto.builder()
                        .datetime(entry.getKey().toString())
                        .userChallengeList(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private UserGoalResponseDto convertToDto(UserGoal userGoal) {
        return UserGoalResponseDto.builder()
                .icon(userGoal.getIcon())
                .title(userGoal.getTitle())
                .status(UserGoalStatus.fromValue(userGoal.getStatus()))
                .difficulty(UserGoalDifficulty.fromValue(userGoal.getDifficulty()))
                .userNickname(userGoal.getUser().getUsername())
                .build();
    }
}
