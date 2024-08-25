package com.stance.infra.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.like.ProjectLikeRepository;
import com.stance.domain.project.ProjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectLikeRepositoryImpl implements ProjectLikeRepository {
    private final ProjectLikeJPARepository projectLikeJPARepository;

    public ProjectLikeRepositoryImpl(ProjectLikeJPARepository projectLikeJPARepository) {
        this.projectLikeJPARepository = projectLikeJPARepository;
    }

    @Override
    public Boolean findByProjectAndCrew(ProjectInfo project, CrewInfo crew) {
        return projectLikeJPARepository.findByProjectAndCrew(project, crew);
    }

    @Override
    public void save(ProjectLikeEntity like) {
        projectLikeJPARepository.save(like);
    }

    @Override
    public List<ProjectLikeEntity> getByProjectName(String projectName) {
        return projectLikeJPARepository.findByProjectName(projectName);

    }

    @Override
    public List<ProjectLikeEntity> getByLikerId(String crewEmail) {
        return projectLikeJPARepository.findByLikerId(crewEmail);
    }
}
