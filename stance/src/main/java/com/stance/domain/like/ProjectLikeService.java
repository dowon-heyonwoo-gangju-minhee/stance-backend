package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import com.stance.infra.like.ProjectLikeEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Component
public class ProjectLikeService {
    private final ProjectLikeRepository projectLikeRepository;

    public ProjectLikeService(ProjectLikeRepository projectLikeRepository) {
        this.projectLikeRepository = projectLikeRepository;
    }

    @Transactional
    public Boolean findByProjectAndCrew(ProjectInfo project, CrewInfo crew) {
        return projectLikeRepository.findByProjectAndCrew(project, crew);

    }

    @Transactional
    public void save(ProjectLikeEntity like) {
        projectLikeRepository.save(like);
    }

    @Transactional
    public List<ProjectLikeInfo> getByProjectName(String projectName) {
        List<ProjectLikeEntity> byProjectName = projectLikeRepository.getByProjectName(projectName);
        return ProjectLikeInfo.from(byProjectName);
    }

    @Transactional
    public List<ProjectLikeInfo> getByLikerId(String crewEmail) {
        List<ProjectLikeEntity> byLikerId = projectLikeRepository.getByLikerId(crewEmail);
        return ProjectLikeInfo.from(byLikerId);
    }
}
