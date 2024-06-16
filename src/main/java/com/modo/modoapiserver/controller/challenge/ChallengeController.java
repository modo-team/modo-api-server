package com.modo.modoapiserver.controller.challenge;

import com.modo.modoapiserver.dto.controller.challenge.UserChallengeDto;
import com.modo.modoapiserver.dto.controller.challenge.UserChallengeListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChallengeController {
    @Operation(summary = "내 주간 목표 목록 조회", description = "내가 이번주 목표를 조회합니다")
    @GetMapping("/my/challenges")
    public ResponseEntity<List<UserChallengeListResponseDto>> getMyChallengeList() {
        List<UserChallengeListResponseDto> userChallengeList = new ArrayList<>(2);

        UserChallengeListResponseDto userChallengeListResponseDto = UserChallengeListResponseDto.builder()
                .datetime("2021-10-10")
                .userChallengeList(new ArrayList<>(2))
                .build();

        userChallengeListResponseDto.getUserChallengeList().add(
                UserChallengeDto.builder()
                        .userNickname("두이")
                        .status("ready")
                        .title("운동하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeListResponseDto.getUserChallengeList().add(
                UserChallengeDto.builder()
                        .userNickname("두이")
                        .status("fail")
                        .title("공부하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeList.add(userChallengeListResponseDto);

        userChallengeListResponseDto = UserChallengeListResponseDto.builder()
                .datetime("2021-10-11")
                .userChallengeList(new ArrayList<>(2))
                .build();


        userChallengeListResponseDto.getUserChallengeList().add(
                UserChallengeDto.builder()
                        .userNickname("두이")
                        .status("ready")
                        .title("운동하기")
                        .difficulty("difficult")
                        .build()
        );

        userChallengeListResponseDto.getUserChallengeList().add(
                UserChallengeDto.builder()
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
