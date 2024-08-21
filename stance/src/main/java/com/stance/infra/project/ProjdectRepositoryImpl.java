package com.stance.infra.project;

import com.stance.domain.project.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjdectRepositoryImpl implements ProjectRepository {
    private final ProjectJPARepository projectJPARepository;

    public ProjdectRepositoryImpl(ProjectJPARepository projectJPARepository) {
        this.projectJPARepository = projectJPARepository;
    }

    @Override
    public List<ProjectEntity> getAll() {
        return projectJPARepository.findAll();
    }
}
