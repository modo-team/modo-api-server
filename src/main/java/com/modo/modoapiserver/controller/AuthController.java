package com.modo.modoapiserver.controller;

import com.modo.modoapiserver.dto.UserDto;
import com.modo.modoapiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> userSignUp(@RequestBody UserDto userDto) {
        userService.registerNewUserAccount(userDto);
        return ResponseEntity.ok().build();
    }


}
