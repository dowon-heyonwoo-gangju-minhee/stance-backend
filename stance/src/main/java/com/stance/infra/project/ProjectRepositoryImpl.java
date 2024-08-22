package com.stance.infra.project;

import com.stance.domain.project.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectJPARepository projectJPARepository;

    public ProjectRepositoryImpl(ProjectJPARepository projectJPARepository) {
        this.projectJPARepository = projectJPARepository;
    }

    @Override
    public List<ProjectEntity> getAll() {
        return projectJPARepository.findAll();
    }

    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectJPARepository.save(projectEntity);
    }

    @Override
    public ProjectEntity getByProjectName(String projectName) {
        return projectJPARepository.findByProjectName(projectName);
    }

    @Override
    public void delete(ProjectEntity projectEntity) {
        projectJPARepository.delete(projectEntity);
    }
}
