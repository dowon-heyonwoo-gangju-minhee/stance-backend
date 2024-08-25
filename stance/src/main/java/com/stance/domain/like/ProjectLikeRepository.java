package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import com.stance.infra.like.ProjectLikeEntity;

import java.util.List;

public interface ProjectLikeRepository {
    Boolean findByProjectAndCrew(ProjectInfo project, CrewInfo crew);

    void save(ProjectLikeEntity like);

    List<ProjectLikeEntity> getByProjectName(String projectName);

    List<ProjectLikeEntity> getByLikerId(String crewEmail);
}
