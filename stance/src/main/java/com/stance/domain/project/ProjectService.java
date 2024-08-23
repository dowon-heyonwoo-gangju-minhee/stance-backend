package com.stance.domain.project;

import com.stance.infra.project.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectInfo> getProjects() {
        List<ProjectEntity> projectEntities = projectRepository.getAll();
        return ProjectInfo.from(projectEntities);
    }

    public ProjectEntity getByProjectName(String projectName) {
        return projectRepository.getByProjectName(projectName);
    }

    public ProjectEntity save(ProjectEntity projectInfo) {
        return projectRepository.save(projectInfo);
    }

    public void delete(ProjectEntity projectEntity) {
        projectRepository.delete(projectEntity);
    }

    public Long getIdByProjectName(String projectName) {
        return projectRepository.getIdByProjectName(projectName);
    }

    public void checkDuplicateProjectName(String s) {
        ProjectEntity byProjectName = projectRepository.getByProjectName(s);
        if (byProjectName != null) {
            throw new IllegalArgumentException("Project name is duplicated");
        }
    }
}
