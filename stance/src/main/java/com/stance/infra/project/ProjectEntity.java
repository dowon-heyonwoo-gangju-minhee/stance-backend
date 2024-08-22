package com.stance.infra.project;

import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.crew.RecruitmentInfoEntity;
import com.stance.infra.period.ExpectedProjectDurationEntity;
import com.stance.infra.period.ExpectedRecruitmentDurationEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    private Long id;
    private String projectName;
    private String description;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<CrewInfoEntity> crewInfoEntity;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<RecruitmentInfoEntity> recruitmentInfoEntity;

    @ManyToOne
    @JoinColumn(name = "expected_project_duration_id")
    private ExpectedProjectDurationEntity expectedProjectDurationEntity;

    @ManyToOne
    @JoinColumn(name = "expected_recruitment_duration_id")
    private ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity;



    public ProjectEntity(String projectName, String description,
                         List<CrewInfoEntity> crewInfoEntity, List<RecruitmentInfoEntity> recruitmentInfoEntity,
                         ExpectedProjectDurationEntity expectedProjectDurationEntity,
                         ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity) {
        this.projectName = projectName;
        this.description = description;
        this.crewInfoEntity = crewInfoEntity;
        this.recruitmentInfoEntity = recruitmentInfoEntity;
        this.expectedProjectDurationEntity = expectedProjectDurationEntity;
        this.expectedRecruitmentDurationEntity = expectedRecruitmentDurationEntity;
    }

    public ProjectEntity() {

    }

    public ProjectEntity(Long id, String projectName, String description,
                         List<CrewInfoEntity> crewInfoEntity, List<RecruitmentInfoEntity> recruitmentInfoEntity,
                         ExpectedProjectDurationEntity expectedProjectDurationEntity, ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.crewInfoEntity = crewInfoEntity;
        this.recruitmentInfoEntity = recruitmentInfoEntity;
        this.expectedProjectDurationEntity = expectedProjectDurationEntity;
        this.expectedRecruitmentDurationEntity = expectedRecruitmentDurationEntity;

    }

    public void addCrewInfo(CrewInfoEntity entity) {
        if(crewInfoEntity == null) {
            crewInfoEntity = new ArrayList<>();
        }
        crewInfoEntity.add(entity);
    }
}
