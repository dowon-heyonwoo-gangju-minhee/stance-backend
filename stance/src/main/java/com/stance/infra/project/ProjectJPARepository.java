package com.stance.infra.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJPARepository extends JpaRepository<ProjectEntity, Long> {
}
