package com.stance.infra.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectJPARepository extends JpaRepository<ProjectEntity, Long> {
    @Query("SELECT p FROM ProjectEntity p WHERE p.projectName = :projectName")
    ProjectEntity findByProjectName(String projectName);
}
