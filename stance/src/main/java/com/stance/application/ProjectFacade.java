package com.stance.application;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.CrewInfoService;
import com.stance.domain.recruitment.RecruitmentInfo;
import com.stance.domain.membership.MembershipService;
import com.stance.domain.period.ExpectedProjectDuration;
import com.stance.domain.period.ExpectedRecruitmentDuration;
import com.stance.domain.project.ProjectCommand;
import com.stance.domain.project.ProjectInfo;
import com.stance.domain.project.ProjectService;
import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.membership.MemberRole;
import com.stance.infra.membership.MembershipEntity;
import com.stance.infra.project.ProjectEntity;
import com.stance.infra.project.ProjectParticipationTracker;
import com.stance.infra.project.ProjectStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ProjectFacade {
    private final ProjectService projectService;
    private final MembershipService membershipService;
    private final CrewInfoService crewService;
    private final ProjectParticipationTracker participationTracker;

    public ProjectFacade(ProjectService projectService, MembershipService membershipService, CrewInfoService crewService, ProjectParticipationTracker participationTracker) {
        this.projectService = projectService;
        this.membershipService = membershipService;
        this.crewService = crewService;
        this.participationTracker = participationTracker;
    }

    @Transactional
    public ProjectInfo enrollProject(ProjectCommand.Apply command) {
        ProjectInfo projectInfo = projectService.getByProjectName(command.projectName());
        CrewInfo crewInfo = crewService.getCrew(command.crewEmail());
        CrewInfoEntity entity = CrewInfo.to(crewInfo);
        ProjectEntity entity1 = ProjectInfo.toEntity(projectInfo);
        entity1.addMember(entity);
        ProjectEntity savedProjectEntity = projectService.save(entity1);
        MembershipEntity membershipEntity = new MembershipEntity(
                entity1,
                entity,
                MemberRole.MEMBER,
                LocalDateTime.now()
        );
        membershipService.save(membershipEntity);
        return ProjectInfo.from(savedProjectEntity);
    }

    @Transactional
    public ProjectInfo createProject(ProjectCommand.Create command) {
        ProjectInfo projectInfo = command.projectInfo();
        projectService.checkDuplicateProjectName(projectInfo.projectName());
        ProjectEntity projectEntity = new ProjectEntity(
                projectInfo.projectName(),
                projectInfo.description(),
                projectInfo.tools(),
                CrewInfo.to(projectInfo.crewInfo()),
                RecruitmentInfo.toEntity(projectInfo.recruitmentInfo()),
                ExpectedProjectDuration.toEntity(projectInfo.expectedProjectDuration()),
                ExpectedRecruitmentDuration.toEntity(projectInfo.expectedRecruitmentDuration())
        );
        ProjectEntity savedProjectEntity = projectService.save(projectEntity);
        return ProjectInfo.from(savedProjectEntity);
    }

    @Transactional
    public ProjectInfo patchProject(ProjectCommand.Patch patch) {
        String projectName = patch.projectName();
        ProjectInfo projectInfo = patch.projectInfo();
        Long id = projectService.getIdByProjectName(projectName);
        ProjectEntity updatedProjectEntity = new ProjectEntity(
                id,
                projectInfo.projectName(),
                projectInfo.description(),
                projectInfo.tools(),
                CrewInfo.to(projectInfo.crewInfo()),
                RecruitmentInfo.toEntity(projectInfo.recruitmentInfo()),
                ExpectedProjectDuration.toEntity(projectInfo.expectedProjectDuration()),
                ExpectedRecruitmentDuration.toEntity(projectInfo.expectedRecruitmentDuration())
        );
        ProjectEntity save = projectService.save(updatedProjectEntity);
        return ProjectInfo.from(save);
    }

    @Transactional
    public Boolean deleteProject(String projectName) {
        ProjectInfo byProjectName = projectService.getByProjectName(projectName);
        projectService.delete(byProjectName);
        return true;
    }

    public Boolean toggleRecruitment(ProjectCommand.State state) {
        String projectName = state.request().projectName();
        ProjectInfo target = projectService.getByProjectName(projectName);
        ProjectEntity projectEntity = ProjectInfo.toEntity(target);
        if (projectEntity.getStatus() == ProjectStatus.COMPLETED) {
            projectEntity.reopenRecruitment();
            projectService.save(projectEntity);
            return false;
        }
        projectEntity.completeRecruitment(participationTracker);
        projectService.save(projectEntity);
        return true;
    }
}

