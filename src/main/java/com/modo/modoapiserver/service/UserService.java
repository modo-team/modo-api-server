package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(UserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getUsername(),
                userDto.getAge(),
                userDto.getMobileNumber()
        );
        return userRepository.save(user);
    }
}
