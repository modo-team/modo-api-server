package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.service.user.UserDto;
import com.modo.modoapiserver.model.User;
import com.modo.modoapiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerNewUserAccount(UserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getUsername(),
                userDto.getAge(),
                userDto.getMobileNumber()
        );
        return userRepository.save(user);
    }

    public User getUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if( user != null && user.getPassword() == password )
            return user;
        else
            return null;
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
