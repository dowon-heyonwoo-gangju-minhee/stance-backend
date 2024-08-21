package com.stance.infra.project;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    private Long id;
    @Getter
    private String projectName;
    @Getter
    private String description;

    @Getter
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<CrewInfoEntity> crewInfoEntity;

    @Getter
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<RecruitmentInfo> recruitmentInfo;

    @Getter
    @ManyToOne
    @JoinColumn(name = "expected_project_duration_id")
    private ExpectedProjectDuration expectedProjectDuration;

    @Getter
    @ManyToOne
    @JoinColumn(name = "expected_recruitment_duration_id")
    private ExpectedRecruitmentDuration expectedRecruitmentDuration;



    public ProjectEntity(String projectName, String description,
                         List<CrewInfoEntity> crewInfoEntity, List<RecruitmentInfo> recruitmentInfo,
                         ExpectedProjectDuration expectedProjectDuration,
                         ExpectedRecruitmentDuration expectedRecruitmentDuration) {
        this.projectName = projectName;
        this.description = description;
        this.crewInfoEntity = crewInfoEntity;
        this.recruitmentInfo = recruitmentInfo;
        this.expectedProjectDuration = expectedProjectDuration;
        this.expectedRecruitmentDuration = expectedRecruitmentDuration;
    }

    public ProjectEntity() {

    }
}
