package com.stance.domain.project;

import com.stance.infra.project.ProjectEntity;
import com.stance.infra.tools.ToolsEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<ProjectEntity> getAll();

    ProjectEntity save(ProjectEntity projectEntity);

    Optional<ProjectEntity> getByProjectName(String projectName);

    void delete(ProjectEntity projectEntity);

    Long getIdByProjectName(String projectName);

    List<ProjectEntity> getFilteredProjects(List<ToolsEntity> tools);

    List<ProjectEntity> getProjectsByDuration(Long durationMonths);

    List<ProjectEntity> getProjectsByPosition(String position);
}
