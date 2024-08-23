package com.stance.domain.project;

import com.stance.infra.project.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<ProjectEntity> getAll();

    ProjectEntity save(ProjectEntity projectEntity);

    Optional<ProjectEntity> getByProjectName(String projectName);

    void delete(ProjectEntity projectEntity);

    Long getIdByProjectName(String projectName);
}
