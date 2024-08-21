package com.stance.domain.project;

import com.stance.infra.project.ProjectEntity;
import com.stance.infra.project.ProjectInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectInfo> getProjects() {
        List<ProjectEntity> projectEntities = projectRepository.getAll();
        return null;
    }
}
