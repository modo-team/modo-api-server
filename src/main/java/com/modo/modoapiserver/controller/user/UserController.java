package com.modo.modoapiserver.controller.user;

import com.modo.modoapiserver.dto.controller.user.UserInfoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/users/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        if (userDetails != null) {
            // userDetails.getUsername() 메서드는 JWT 토큰에서 추출한 사용자 이름을 반환합니다.
            String username = userDetails.getUsername();
            UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(1L, userDetails.getUsername(), userDetails.getUsername());
            return ResponseEntity.ok(userInfoResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
