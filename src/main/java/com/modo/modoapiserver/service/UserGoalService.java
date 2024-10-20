package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.service.usergoal.UserGoalDto;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.repository.UserGoalRepository;
import com.modo.modoapiserver.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserGoalService {
    @Autowired
    UserGoalRepository userGoalRepository;

    @Transactional
    public UserGoal saveUserGoal(UserGoalDto userGoalDto) {
        UserGoal userGoal = UserGoal.builder()
                .title(userGoalDto.getTitle())
                .icon(userGoalDto.getIcon())
                .difficulty(userGoalDto.getDifficulty().getValue())
                .teamId(userGoalDto.getTeamId())
                .categoryId(userGoalDto.getCategoryId())
                .verificationMethod(userGoalDto.getVerificationMethod())
                .goalDatetime(userGoalDto.getGoalDatetime())
                .userId(userGoalDto.getUserId())
                .status(userGoalDto.getStatus().getValue())
                .build();
        userGoalRepository.save(userGoal);
        return userGoal;
    }

    public UserGoal getUserGoal(Long id) {
        return userGoalRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteUserGoal(Long id) {
        userGoalRepository.deleteById(id);
    }

    @Transactional
    public void updateUserGoal(Long id, UserGoalDto userGoalDto) {
        UserGoal userGoal = userGoalRepository.findById(id).orElseThrow();
        userGoal.setTitle(userGoalDto.getTitle());
        userGoal.setIcon(userGoalDto.getIcon());
        userGoal.setDifficulty(userGoalDto.getDifficulty().getValue());
        userGoal.setTeamId(userGoalDto.getTeamId());
        userGoal.setCategoryId(userGoalDto.getCategoryId());
        userGoal.setVerificationMethod(userGoalDto.getVerificationMethod());
        userGoal.setGoalDatetime(userGoalDto.getGoalDatetime());
        userGoal.setStatus(userGoalDto.getStatus().getValue());
        userGoalRepository.save(userGoal);
    }
    @Transactional
    public void updateUserGoal(UserGoal userGoal) {
        userGoalRepository.save(userGoal);
    }
    public List<UserGoal> getUserGoalsBetween(Long userId, LocalDateTime start, LocalDateTime end) {
        return userGoalRepository.findByUserIdAndGoalDatetimeBetween(userId, start, end);
    }

    public boolean isUserGoalAccessible(Long userGoalId, CustomUserDetails userDetails) {
        try {
            UserGoal userGoal = this.getUserGoal(userGoalId);
            if (!userGoal.getUserId().equals(userDetails.getIdentity())) {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}