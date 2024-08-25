package com.stance.infra.project;

import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.like.ProjectLikeEntity;
import com.stance.infra.recruitment.RecruitmentInfoEntity;
import com.stance.infra.membership.MemberRole;
import com.stance.infra.membership.MembershipEntity;
import com.stance.infra.period.ExpectedProjectDurationEntity;
import com.stance.infra.period.ExpectedRecruitmentDurationEntity;
import com.stance.infra.tools.ToolsEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String description;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<MembershipEntity> memberships;

    @OneToMany(mappedBy = "project")
    private List<ProjectLikeEntity> likes = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<ToolsEntity> tools;


    @OneToMany
    @JoinColumn(name = "project_id")
    private List<RecruitmentInfoEntity> recruitmentInfoEntity;

    @ManyToOne
    @JoinColumn(name = "expected_project_duration_id")
    private ExpectedProjectDurationEntity expectedProjectDurationEntity;

    @ManyToOne
    @JoinColumn(name = "expected_recruitment_duration_id")
    private ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity;


    public ProjectEntity(String projectName, String description,List<Tools> tools,
                         CrewInfoEntity crewInfoEntity, List<RecruitmentInfoEntity> recruitmentInfoEntity,
                         ExpectedProjectDurationEntity expectedProjectDurationEntity,
                         ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity) {
        this.projectName = projectName;
        this.description = description;
        this.recruitmentInfoEntity = recruitmentInfoEntity;
        this.expectedProjectDurationEntity = expectedProjectDurationEntity;
        this.expectedRecruitmentDurationEntity = expectedRecruitmentDurationEntity;
        setOwner(crewInfoEntity);
    }

    public ProjectEntity() {

    }

    public ProjectEntity(Long id, String projectName, String description, List<Tools> tools,
                         CrewInfoEntity crewInfoEntity, List<RecruitmentInfoEntity> recruitmentInfoEntity,
                         ExpectedProjectDurationEntity expectedProjectDurationEntity, ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity) {
        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.recruitmentInfoEntity = recruitmentInfoEntity;
        this.expectedProjectDurationEntity = expectedProjectDurationEntity;
        this.expectedRecruitmentDurationEntity = expectedRecruitmentDurationEntity;
        setOwner(crewInfoEntity);
    }
    public void addLike(CrewInfoEntity crew) {
        ProjectLikeEntity like = new ProjectLikeEntity(this, crew, LocalDateTime.now());
        this.likes.add(like);
    }

    public CrewInfoEntity getOwner() {
        return memberships.stream()
                .filter(pm -> pm.getRole() == MemberRole.OWNER)
                .findFirst()
                .map(MembershipEntity::getCrew)
                .orElse(null);
    }

    public void setOwner(CrewInfoEntity owner) {
        MembershipEntity ownerMembership =
                new MembershipEntity(this, owner, MemberRole.OWNER, LocalDateTime.now());
        memberships.add(ownerMembership);
    }

    public void addMember(CrewInfoEntity member) {
        MembershipEntity membership =
                new MembershipEntity(this, member, MemberRole.MEMBER, LocalDateTime.now());
        memberships.add(membership);
    }

    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.RECRUITING;



    public void completeRecruitment(ProjectParticipationTracker tracker) {
        if (this.status != ProjectStatus.RECRUITING) {
            throw new IllegalStateException("Cannot complete recruitment for a project that is not in recruiting state");
        }

        if (tracker.markAsCompleted(this.id)) {
            this.status = ProjectStatus.COMPLETED;
            for (MembershipEntity membership : memberships) {
                membership.getCrew().incrementProjectParticipation();
            }
        }
    }

    public void reopenRecruitment() {
        if (this.status == ProjectStatus.COMPLETED) {
            this.status = ProjectStatus.RECRUITING;
        }
    }
}
