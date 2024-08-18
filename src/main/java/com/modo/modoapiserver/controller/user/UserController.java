package com.modo.modoapiserver.controller.user;

import com.modo.modoapiserver.dto.controller.user.RequestUserInfoUpdateDto;
import com.modo.modoapiserver.dto.controller.user.ResponseUserInfoDto;
import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "사용자 프로필 조회", description = "사용자의 프로필을 조회합니다")
    @GetMapping("/users/profile")
    public ResponseEntity<ResponseUserInfoDto> getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails != null) {
            // userDetails.getUsername() 메서드는 JWT 토큰에서 추출한 사용자 이름을 반환합니다.
            Long userId = Long.parseLong(userDetails.getUsername());
            User user = userService.getUserById(userId);
            ResponseUserInfoDto responseUserInfoDto = new ResponseUserInfoDto(user.getId(), user.getEmail(), user.getUsername(), user.getBirth(), user.getGender());
            return ResponseEntity.ok(responseUserInfoDto);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "사용자 정보 업데이트",
            description = "유저의 주요 정보를 업데이트합니다. 카카오 로그인시에 사용자 정보가 넘어오지 않는 경우, 이 API를 통해 사용자 정보를 업데이트합니다")
    @PutMapping("/users")
    public ResponseUserInfoDto updateUserInfo(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RequestUserInfoUpdateDto requestUpdateUserInfoDto){
        Long userId = Long.parseLong(userDetails.getUsername());
        UserDto userDto = UserDto.builder()
                .username(requestUpdateUserInfoDto.getUserName())
                .birth(requestUpdateUserInfoDto.getUserBirth().toString())
                .email(requestUpdateUserInfoDto.getUserEmail())
                .gender(requestUpdateUserInfoDto.getUserGender())
                .build();
        User user = userService.updateUserInfo(userId, userDto);
        return ResponseUserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getUsername())
                .birth(user.getBirth())
                .gender(user.getGender())
                .build();
    }
}
