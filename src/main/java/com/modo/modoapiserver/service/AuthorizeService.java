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
        } else if (!userRepository.findByExternalId(userDto.getExternalId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "External ID already in use.");
        } else if (userDto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required.");
        }
        User user = new User(userDto);
        return userRepository.save(user);
    }
    public User registerExternalUser(String externalId, String externalType){
        if (!userRepository.findByExternalId(externalId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "External ID already in use.");
        }

        User user = User.builder()
                .externalId(externalId)
                .password(externalId)
                .externalType(externalType)
                .build();
        return userRepository.save(user);
    }

    public LoginResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Invalid credentials");
        }
        return new LoginResponseDto(user.getId(), jwtUtil.generateToken(user));
    }

    public LoginResponseDto externalLogin(String externalId, String externalType) {
        User user = userRepository.findByExternalIdAndExternalType(externalId, externalType)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with external ID: " + externalId + " and external type: " + externalType));

        return LoginResponseDto.builder()
                .id(user.getId())
                .accessToken(jwtUtil.generateToken(user))
                .build();


    }
}
