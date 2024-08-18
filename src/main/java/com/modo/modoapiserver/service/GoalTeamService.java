package com.modo.modoapiserver.service;

import com.modo.modoapiserver.dto.service.goalteam.GoalTeamDto;
import com.modo.modoapiserver.model.GoalTeam;
import com.modo.modoapiserver.repository.GoalTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalTeamService {
    @Autowired
    GoalTeamRepository goalTeamRepository;

    private GoalTeamDto convertToDto(GoalTeam goalTeam) {
        return GoalTeamDto.builder().id(goalTeam.getId()).name(goalTeam.getName()).startDate(goalTeam.getStartDate())
                .endDate(goalTeam.getEndDate()).description(goalTeam.getDescription())
                .applymentQuestion(goalTeam.getApplymentQuestion()).maxMemberNumber(goalTeam.getMaxMemberNumber())
                .teamCategory(goalTeam.getTeamCategory()).build();
    }

    public GoalTeamDto createTeam(GoalTeamDto goalTeamDto) {
        GoalTeam goalTeam = GoalTeam.builder().teamCategory(goalTeamDto.getTeamCategory())
                .description(goalTeamDto.getDescription()).endDate(goalTeamDto.getEndDate()).id(goalTeamDto.getId())
                .maxMemberNumber(goalTeamDto.getMaxMemberNumber()).name(goalTeamDto.getName())
                .startDate(goalTeamDto.getStartDate()).build();
        goalTeamRepository.save(goalTeam);

        return convertToDto(goalTeam);
    }

    public void deleteTeamById(Long id) {
        goalTeamRepository.deleteById(id);
    }

    public GoalTeamDto updateTeam(GoalTeamDto goalTeamDto) {
        GoalTeam goalTeam = GoalTeam.builder().teamCategory(goalTeamDto.getTeamCategory())
                .description(goalTeamDto.getDescription()).endDate(goalTeamDto.getEndDate()).id(goalTeamDto.getId())
                .maxMemberNumber(goalTeamDto.getMaxMemberNumber()).name(goalTeamDto.getName())
                .startDate(goalTeamDto.getStartDate()).build();
        goalTeamRepository.save(goalTeam);

        return convertToDto(goalTeam);
    }

    public GoalTeamDto getTeamById(Long id) {
        GoalTeam goalTeam = goalTeamRepository.findById(id).orElseThrow();
        return convertToDto(goalTeam);
    }
}
