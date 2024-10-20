package com.modo.modoapiserver.controller.usergoal;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.modo.modoapiserver.config.S3Config;
import com.modo.modoapiserver.dto.controller.usergoal.RequestCreateUserGoalDto;
import com.modo.modoapiserver.dto.controller.usergoal.ResponseUserGoalDto;
import com.modo.modoapiserver.dto.controller.usergoal.ResponseUserGoalListDto;
import com.modo.modoapiserver.dto.controller.usergoal.RequestUserGoalDto;
import com.modo.modoapiserver.dto.service.usergoal.UserGoalDto;
import com.modo.modoapiserver.enums.UserGoalDifficulty;
import com.modo.modoapiserver.enums.UserGoalStatus;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.security.CustomUserDetails;
import com.modo.modoapiserver.service.UserGoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserGoalController {
    @Autowired
    private UserGoalService userGoalService;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private S3Config s3Config;

    @Operation(summary = "목표 생성하기", description = "목표를 생성합니다. 날짜가 여러개 들어오면 다른 날짜의 같은 목표가 다중으로 생성됩니다.")
    @PostMapping("/goals")
    public ResponseEntity<List<UserGoalDto>> saveUserGoal(@RequestBody RequestCreateUserGoalDto data, @AuthenticationPrincipal CustomUserDetails userDetails){
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
                        .difficulty(UserGoalDifficulty.fromValue(userGoal.getDifficulty()))
                        .teamId(userGoal.getTeamId())
                        .categoryId(userGoal.getCategoryId())
                        .verificationMethod(userGoal.getVerificationMethod())
                        .userId(userGoal.getUserId())
                        .status(UserGoalStatus.fromValue(userGoal.getStatus()))
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(userGoalDtoList);
    }

    @Operation(summary = "목표 상세 가져오기", description = "목표 상세를 가져옵니다")
    @GetMapping("/goal/{id}")
    public ResponseEntity<UserGoalDto> getUserGoal(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        UserGoal userGoal = userGoalService.getUserGoal(id);

        boolean isUserGoalAccessible = userGoalService.isUserGoalAccessible(id, userDetails);

        if(!isUserGoalAccessible) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 목표에 접근할 권한이 없습니다.");
        }

        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

        URL presignedUrl = this.s3Client.generatePresignedUrl(
                s3Config.getUserGoalBucketName(),
                userGoal.getCompleteVerificationPictureUrl(),
                expiration
        );

        UserGoalDto userGoalDto = UserGoalDto.builder()
                .id(userGoal.getId())
                .goalDatetime(userGoal.getGoalDatetime())
                .title(userGoal.getTitle())
                .icon(userGoal.getIcon())
                .difficulty(UserGoalDifficulty.fromValue(userGoal.getDifficulty()))
                .teamId(userGoal.getTeamId())
                .categoryId(userGoal.getCategoryId())
                .verificationMethod(userGoal.getVerificationMethod())
                .userId(userGoal.getUserId())
                .status(UserGoalStatus.fromValue(userGoal.getStatus()))
                .completeVerificationPictureUrl(presignedUrl.toString())
                .build();

        return ResponseEntity.ok(userGoalDto);
    }

    @Operation(summary = "목표 지우기", description = "목표를 삭제합니다.")
    @DeleteMapping("/goal/{id}")
    public ResponseEntity<?> deleteUserGoal(@PathVariable("id") Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        boolean isUserGoalAccessible = userGoalService.isUserGoalAccessible(id, userDetails);

        if(!isUserGoalAccessible) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 목표에 접근할 권한이 없습니다.");
        }

        userGoalService.deleteUserGoal(id);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "목표 업데이트", description = "목표를 업데이트합니다.")
    @PatchMapping("/goal/{id}")
    public ResponseEntity<?> patchUserGoal(@PathVariable("id") Long id, @RequestBody RequestUserGoalDto requestUserGoalDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        // TODO: jwt 토큰에서 사용자 정보를 가져와서 사용자의 목표인지 확인하는 로직 추가
        UserGoal userGoal = userGoalService.getUserGoal(id);

        boolean isUserGoalAccessible = userGoalService.isUserGoalAccessible(id, userDetails);

        if(!isUserGoalAccessible) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 목표에 접근할 권한이 없습니다.");
        }

        if( requestUserGoalDto.getTitle() != null) {
            userGoal.setTitle(requestUserGoalDto.getTitle());
        }
        if( requestUserGoalDto.getIcon() != null) {
            userGoal.setIcon(requestUserGoalDto.getIcon());
        }
        if( requestUserGoalDto.getDifficulty() != null) {
            userGoal.setDifficulty(requestUserGoalDto.getDifficulty().getValue());
        }
        if( requestUserGoalDto.getTeamId() != null) {
            userGoal.setTeamId(requestUserGoalDto.getTeamId());
        }
        if( requestUserGoalDto.getCategoryId() != null) {
            userGoal.setCategoryId(requestUserGoalDto.getCategoryId());
        }
        if( requestUserGoalDto.getVerificationMethod() != null) {
            userGoal.setVerificationMethod(requestUserGoalDto.getVerificationMethod());
        }
        if( requestUserGoalDto.getGoalDatetime() != null) {
            userGoal.setGoalDatetime(requestUserGoalDto.getGoalDatetime());
        }
        if( requestUserGoalDto.getStatus() != null) {
            userGoal.setStatus(requestUserGoalDto.getStatus().getValue());
        }

        userGoalService.updateUserGoal(userGoal);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 주간 목표 목록 조회", description = "내가 생성한 이번주 목표를 조회합니다")
    @GetMapping("/my/challenges")
    public ResponseEntity<List<ResponseUserGoalListDto>> getMyChallengeList(
            @AuthenticationPrincipal CustomUserDetails userDetails,

            @RequestParam(value="start_date", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(value="end_date", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate) {
        if( startDate == null && endDate == null) {
            startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.MONDAY);
            endDate = startDate.plusDays(7).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        }
        else if ( startDate == null || endDate == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"startDate 와 endDate 둘 다 넣거나 둘 다 안 넣어야 합니다.");
        }

        List<UserGoal> userGoalsThisWeek = this.userGoalService.getUserGoalsBetween(userDetails.getIdentity(), startDate, endDate);

        // 가져온 userGoalsThisWeek 를 년/월/일 을 기준으로 Grouping 해서, UserGoalListResponseDto 를 만들어서 반환한다.
        Map<LocalDate, List<ResponseUserGoalDto>> groupedGoals = userGoalsThisWeek.stream()
                .collect(Collectors.groupingBy(
                        userGoal -> userGoal.getGoalDatetime().toLocalDate(),
                        Collectors.mapping(this::convertToDto, Collectors.toList())
                ));

        List<ResponseUserGoalListDto> response = groupedGoals.entrySet().stream()
                .map(entry -> ResponseUserGoalListDto.builder()
                        .datetime(entry.getKey().toString())
                        .userChallengeList(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private ResponseUserGoalDto convertToDto(UserGoal userGoal) {
        return ResponseUserGoalDto.builder()
                .id(userGoal.getId())
                .icon(userGoal.getIcon())
                .title(userGoal.getTitle())
                .status(UserGoalStatus.fromValue(userGoal.getStatus()))
                .difficulty(UserGoalDifficulty.fromValue(userGoal.getDifficulty()))
                .userNickname(userGoal.getUser().getUsername())
                .build();
    }

    @Operation(summary = "목표 성공처리(인증)", description = "목표 인증 사진을 업로드하며, 목표를 완료 처리합니다. request body는 form-data 로 요청 바랍니다.")
    @PostMapping(value="/goals/{id}/complete", consumes = "multipart/form-data")
    public ResponseEntity<?> completeUserGoal(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        UserGoal userGoal = userGoalService.getUserGoal(id);

        boolean isUserGoalAccessible = userGoalService.isUserGoalAccessible(id, userDetails);

        if(!isUserGoalAccessible) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 목표에 접근할 권한이 없습니다.");
        }

        String bucketName = "modo-static-bucket";
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName =
                "%s_%s%s".formatted(id, System.currentTimeMillis(), fileExtension);
        this.s3Client.putObject(bucketName, fileName, file.getInputStream(), null);

        userGoal.setStatus(UserGoalStatus.SUCCESS.getValue());
        userGoal.setCompleteVerificationPictureUrl(fileName);

        userGoalService.updateUserGoal(userGoal);

        return ResponseEntity.ok().build();
    }
}
