package com.modo.modoapiserver.controller.auth;

import com.modo.modoapiserver.dto.controller.auth.LoginResponseDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthorizeService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestParam String email, @RequestParam String password) {
        User newUser = authService.registerUser(email, password);
        return ResponseEntity.ok("login succeeded");
    }

    @PostMapping("/sign-in")
    public LoginResponseDto login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }
}
