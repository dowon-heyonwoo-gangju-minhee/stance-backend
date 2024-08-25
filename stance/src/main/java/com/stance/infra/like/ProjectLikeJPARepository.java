package com.stance.infra.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectLikeJPARepository extends JpaRepository<ProjectLikeEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(pl) > 0 THEN TRUE ELSE FALSE END FROM ProjectLikeEntity pl WHERE pl.project = :project AND pl.crew = :crew")
    Boolean findByProjectAndCrew(ProjectInfo project, CrewInfo crew);

    @Query("SELECT pl FROM ProjectLikeEntity pl WHERE pl.project.projectName = :projectName")
    List<ProjectLikeEntity> findByProjectName(String projectName);

    @Query("SELECT pl FROM ProjectLikeEntity pl WHERE pl.crew.githubEmail = :crewEmail")
    List<ProjectLikeEntity> findByLikerId(String crewEmail);
}
