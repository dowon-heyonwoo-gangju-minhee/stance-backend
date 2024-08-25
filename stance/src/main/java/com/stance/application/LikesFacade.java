package com.stance.application;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.CrewInfoService;
import com.stance.domain.like.*;
import com.stance.domain.project.ProjectInfo;
import com.stance.domain.project.ProjectService;
import com.stance.infra.like.CrewLikeEntity;
import com.stance.infra.like.CrewLikeInfo;
import com.stance.infra.like.ProjectLikeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LikesFacade {
    private final ProjectService projectService;
    private final CrewInfoService crewInfoService;
    private final ProjectLikeService projectLikeService;
    private final CrewLikeService crewLikeService;

    public LikesFacade(ProjectService projectService, CrewInfoService crewInfoService, ProjectLikeRepository projectLikeRepository, CrewLikeRepository crewLikeRepository, ProjectLikeService projectLikeService, CrewLikeService crewLikeService) {
        this.projectService = projectService;
        this.crewInfoService = crewInfoService;
        this.projectLikeService = projectLikeService;
        this.crewLikeService = crewLikeService;
    }


    public Boolean likeProject(LikeCommand.Like command) {
        ProjectInfo project = projectService.getByProjectName(command.projectName());
        CrewInfo crew = crewInfoService.getCrew(command.crewEmail());


        if (projectLikeService.findByProjectAndCrew(project, crew)) {
            throw new IllegalStateException("This crew has already liked this project");
        }

        ProjectLikeEntity like = new ProjectLikeEntity(
                ProjectInfo.toEntity(project),
                CrewInfo.to(crew), LocalDateTime.now());
        try{
            projectLikeService.save(like);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void likeCrew(String likerEmail, String likedCrewEmail) {
        CrewInfo liker = crewInfoService.getCrew(likerEmail);
        CrewInfo likedCrew = crewInfoService.getCrew(likedCrewEmail);

        if (crewLikeService.findByLikerAndLikedCrew(liker, likedCrew)) {
            throw new IllegalStateException("This crew has already liked the other crew");
        }

        CrewLikeEntity like = new CrewLikeEntity(
                CrewInfo.to(liker),
                CrewInfo.to(likedCrew));
        crewLikeService.save(like);
    }

    public List<CrewInfo> getProjectLikers(String projectName) {
        return projectLikeService.getByProjectName(projectName)
                .stream()
                .map(CrewInfo::from)
                .collect(Collectors.toList());

    }

    public List<ProjectInfo> getLikedProjects(String crewEmail) {
        return projectLikeService.getByLikerId(crewEmail)
                .stream()
                .map(ProjectInfo::from)
                .collect(Collectors.toList());
    }

    public List<CrewInfo> getLikedCrews(String likerEmail) {
        List<CrewLikeInfo> byLikerId = crewLikeService.getByLikerId(likerEmail);
        return byLikerId.stream()
                .map(CrewInfo::from)
                .collect(Collectors.toList());
    }
}