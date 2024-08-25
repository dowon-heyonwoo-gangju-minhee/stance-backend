package com.stance.domain.project;

import com.stance.domain.tools.Tools;
import com.stance.infra.project.ProjectEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
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

    public ProjectInfo getByProjectName(String projectName) {
        ProjectEntity projectEntity = projectRepository.getByProjectName(projectName)
                .orElseThrow(EntityNotFoundException::new);
        return ProjectInfo.from(projectEntity);
    }

    public ProjectEntity save(ProjectEntity projectInfo) {
        return projectRepository.save(projectInfo);
    }

    public void delete(ProjectInfo projectInfo) {
        ProjectEntity projectEntity = ProjectInfo.toEntity(projectInfo);
        projectRepository.delete(projectEntity);
    }

    public Long getIdByProjectName(String projectName) {
        return projectRepository.getIdByProjectName(projectName);
    }

    public void checkDuplicateProjectName(String s) {
        ProjectEntity byProjectName = projectRepository.getByProjectName(s)
                .orElse(null);
        if (byProjectName != null) {
            throw new IllegalArgumentException("Project name is duplicated");
        }
    }

    public List<ProjectInfo> getFilteredProjects(ProjectCommand.Filter filter) {
        List<ProjectEntity> projectEntities = projectRepository.getFilteredProjects(Tools.toEntity(filter.tools()));
        return ProjectInfo.from(projectEntities);
    }
    public List<ProjectInfo> getProjectsByDuration(ProjectCommand.DateFilter dateFilter) {
        List<ProjectEntity> projectEntities = projectRepository.getProjectsByDuration(dateFilter.durationMonths());
        return ProjectInfo.from(projectEntities);
    }

    public List<ProjectInfo> getProjectsByPosition(ProjectCommand.PositionFilter positionFilter) {
        List<ProjectEntity> projectEntities = projectRepository.getProjectsByPosition(positionFilter.position());
        return ProjectInfo.from(projectEntities);
    }
}
