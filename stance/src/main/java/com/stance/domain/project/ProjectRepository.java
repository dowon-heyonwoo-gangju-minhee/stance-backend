package com.stance.domain.project;

import com.stance.infra.project.ProjectEntity;

import java.util.List;

public interface ProjectRepository {
    List<ProjectEntity> getAll();
}
