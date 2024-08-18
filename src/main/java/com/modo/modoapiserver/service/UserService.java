package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(UserDto userDto) {
        User user = User.builder().email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .mobileNumber(userDto.getMobileNumber())
                .birth(userDto.getBirth())
                .build();
        return userRepository.save(user);
    }

    public User getUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        if( user.getPassword().equals(password) )
            return user;
        else
            return null;
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return user;
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return user;
    }

    public User updateUserInfo(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setUsername(userDto.getUsername());
        user.setBirth(userDto.getBirth());
        user.setGender(userDto.getGender());
        return userRepository.save(user);
    }
}
