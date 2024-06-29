package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.controller.goal.UserGoalRequestDto;
import com.modo.modoapiserver.dto.service.userGoal.UserGoalDto;
import com.modo.modoapiserver.model.UserGoal;
import com.modo.modoapiserver.repository.UserGoalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalService {
    @Autowired
    UserGoalRepository userGoalRepository;

    @Transactional
    public UserGoal saveUserGoal(UserGoalDto userGoalDto) {
        UserGoal userGoal = UserGoal.builder()
                .title(userGoalDto.getTitle())
                .icon(userGoalDto.getIcon())
                .difficulty(userGoalDto.getDifficulty())
                .teamId(userGoalDto.getTeamId())
                .categoryId(userGoalDto.getCategoryId())
                .verificationMethod(userGoalDto.getVerificationMethod())
                .goalDatetime(userGoalDto.getGoalDatetime())
                .build();
        userGoalRepository.save(userGoal);
        return userGoal;
    }
}
