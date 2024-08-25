package com.stance.infra.project;

import com.stance.infra.tools.ToolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectJPARepository extends JpaRepository<ProjectEntity, Long> {
    @Query("SELECT p FROM ProjectEntity p WHERE p.projectName = :projectName")
    ProjectEntity findByProjectName(String projectName);

    @Query("SELECT p.id FROM ProjectEntity p WHERE p.projectName = :projectName")
    Long getIdByProjectName(String projectName);

    @Query("SELECT p FROM ProjectEntity p JOIN p.tools t WHERE t IN :tools")
    List<ProjectEntity> findByToolsIn( List<ToolsEntity> tools);


    @Query("SELECT p FROM ProjectEntity p WHERE p.expectedProjectDurationEntity.startDate <= CURRENT_DATE " +
            "AND p.expectedProjectDurationEntity.endDate >= CURRENT_DATE - :durationMonths")
    List<ProjectEntity> findByDurationMonths(Long durationMonths);

    @Query("SELECT DISTINCT p FROM ProjectEntity p JOIN p.recruitmentInfoEntity r WHERE r.position = :position")
    List<ProjectEntity> findByPosition(String position);
}
