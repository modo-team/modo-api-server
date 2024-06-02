package com.modo.modoapiserver.controller.auth;

import com.modo.modoapiserver.dto.controller.auth.LoginResponseDto;
import com.modo.modoapiserver.dto.controller.auth.OauthLoginRequestDto;
import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.service.AuthorizeService;
import com.modo.modoapiserver.service.KakaoOAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthorizeService authService;

    @Autowired
    private KakaoOAuth2Service kakaoOAuth2Service;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestParam String email, @RequestParam String password) {
        User newUser = authService.registerUser(email, password);
        return ResponseEntity.ok("signup succeeded");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponseDto> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(authService.login(email, password));
    }

    @PostMapping("/kakao/sign-up")
    public ResponseEntity<?> kakaoSignUp(@RequestParam String code, @RequestParam String redirectUri) {
        String kakaoAuthorizationCode = code;
        String accessToken = kakaoOAuth2Service.getKakaoAccessToken(kakaoAuthorizationCode, redirectUri);
        Map<String, Object> userInfo = kakaoOAuth2Service.getKakaoUserInfo(accessToken);
        UserDto userDto = new UserDto();
        userDto.setExternalId(userInfo.get("id").toString());
        userDto.setPassword(userInfo.get("id").toString()); // 카카오 ID를 비밀번호로 사용
        authService.registerUser(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/kakao/sign-in")
    public ResponseEntity<LoginResponseDto> kakaoSignIn(@RequestBody OauthLoginRequestDto data) {
        String kakaoAuthorizationCode = data.getCode();
        String accessToken = kakaoOAuth2Service.getKakaoAccessToken(kakaoAuthorizationCode, data.getRequestUri());
        Map<String, Object> userInfo = kakaoOAuth2Service.getKakaoUserInfo(accessToken);
        String externalId = userInfo.get("id").toString();
        return ResponseEntity.ok(authService.login(externalId));
    }
}
