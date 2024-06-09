package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.controller.auth.LoginResponseDto;
import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.repository.UserRepository;
import com.modo.modoapiserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.util.StringUtils.hasText;

@Service
public class AuthorizeService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(UserDto userDto) {
        if (!userRepository.findByEmail(userDto.getEmail()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use.");
        }

        if (!userDto.hasValidPassword()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid password is required.");
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .username(userDto.getUsername())
                .mobileNumber(userDto.getMobileNumber())
                .birth(userDto.getBirth())
                .gender(userDto.getGender())
                .build();
        return userRepository.save(user);
    }
    public LoginResponseDto registerExternalUser(String externalId, String externalType){
        User user = userRepository.findByExternalIdAndExternalType(externalId, externalType)
                .orElseGet(() -> User.builder()
                        .externalId(externalId)
                        .password(externalId)
                        .externalType(externalType)
                        .build()
                );

        if (user.isEmpty()) {
            userRepository.save(user);
        }

        return LoginResponseDto.builder()
                .id(user.getId())
                .accessToken(jwtUtil.generateToken(user))
                .build();
    }

    public LoginResponseDto login(String email, String password) {
        User user = userRepository.findByEmailAndExternalTypeIsNull(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid credentials");
        }

        return LoginResponseDto.builder()
                .id(user.getId())
                .accessToken(jwtUtil.generateToken(user))
                .build();
    }
}
