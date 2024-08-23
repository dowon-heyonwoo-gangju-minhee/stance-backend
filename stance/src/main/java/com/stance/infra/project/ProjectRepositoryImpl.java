package com.stance.infra.project;

import com.stance.domain.project.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<ProjectEntity> getByProjectName(String projectName) {
        ProjectEntity byProjectName = projectJPARepository.findByProjectName(projectName);
        return Optional.ofNullable(byProjectName);
    }

    @Override
    public void delete(ProjectEntity projectEntity) {
        projectJPARepository.delete(projectEntity);
    }

    @Override
    public Long getIdByProjectName(String projectName) {
        return projectJPARepository.getIdByProjectName(projectName);
    }
}
