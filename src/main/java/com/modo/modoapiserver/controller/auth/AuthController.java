package com.modo.modoapiserver.controller.auth;

import com.modo.modoapiserver.dto.controller.auth.ResponseLoginDto;
import com.modo.modoapiserver.dto.controller.auth.RequestOauthLoginDto;
import com.modo.modoapiserver.dto.controller.auth.RequestSignInDto;
import com.modo.modoapiserver.dto.controller.auth.RequestSignUpDto;
import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.service.AuthorizeService;
import com.modo.modoapiserver.service.KakaoOAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(summary = "이메일 회원가입", description = "회원가입을 진행합니다")
    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody RequestSignUpDto data) {
        UserDto userDto = new UserDto(
                data.getEmail(),
                data.getPassword(),
                data.getNickname(),
                data.getGender(),
                data.getBirth()
        );
        User newUser = authService.registerUser(userDto);
        return ResponseEntity.ok("signup succeeded");
    }

    @Operation(summary = "이메일 로그인", description = "로그인을 진행합니다")
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody RequestSignInDto data) {
        return ResponseEntity.ok(authService.login(data.getEmail(), data.getPassword()));
    }

    @Operation(summary = "카카오 로그인/회원가입", description = "카카오 로그인 및 회원가입을 진행합니다")
    @PostMapping("/oauth/kakao")
    public ResponseEntity<ResponseLoginDto> kakaoSignUp(@RequestBody RequestOauthLoginDto data) {
        String accessToken = data.getAccessToken();
        if (accessToken == null || accessToken.isEmpty()) {
            String kakaoAuthorizationCode = data.getCode();
            accessToken = kakaoOAuth2Service.getKakaoAccessToken(kakaoAuthorizationCode, data.getRequestUri(), data.getCodeVerifier());
        }
        Map<String, Object> userInfo = kakaoOAuth2Service.getKakaoUserInfo(accessToken);
        return ResponseEntity.ok(authService.registerExternalUser(userInfo.get("id").toString(), "kakao"));
    }
}
