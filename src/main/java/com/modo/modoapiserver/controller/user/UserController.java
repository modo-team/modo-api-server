package com.modo.modoapiserver.controller.user;

import com.modo.modoapiserver.dto.controller.user.UserInfoResponseDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.repository.UserRepository;
import com.modo.modoapiserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "사용자 프로필 조회", description = "사용자의 프로필을 조회합니다")
    @GetMapping("/users/profile")
    public ResponseEntity<UserInfoResponseDto> getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails != null) {
            // userDetails.getUsername() 메서드는 JWT 토큰에서 추출한 사용자 이름을 반환합니다.
            Long userId = Long.parseLong(userDetails.getUsername());
            User user = userService.getUserById(userId);
            UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(user.getId(), user.getEmail(), user.getUsername(), user.getBirth(), user.getGender());
            return ResponseEntity.ok(userInfoResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
