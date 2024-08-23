package com.stance.application;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.CrewService;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ProjectFacade {
    private final ProjectService projectService;
    private final MembershipService membershipService;
    private final CrewService crewService;

    public ProjectFacade(ProjectService projectService, MembershipService membershipService, CrewService crewService) {
        this.projectService = projectService;
        this.membershipService = membershipService;
        this.crewService = crewService;
    }

    @Transactional
    public ProjectInfo enrollProject(ProjectCommand.Apply command) {
        ProjectEntity projectInfo = projectService.getByProjectName(command.projectName());
        CrewInfo crewInfo = crewService.getCrew(command.crewEmail());
        CrewInfoEntity entity = CrewInfo.toEntity(crewInfo);
        projectInfo.addMember(entity);
        ProjectEntity savedProjectEntity = projectService.save(projectInfo);
        MembershipEntity membershipEntity = new MembershipEntity(
                projectInfo,
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
                CrewInfo.toEntity(projectInfo.crewInfo()),
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
                CrewInfo.toEntity(projectInfo.crewInfo()),
                RecruitmentInfo.toEntity(projectInfo.recruitmentInfo()),
                ExpectedProjectDuration.toEntity(projectInfo.expectedProjectDuration()),
                ExpectedRecruitmentDuration.toEntity(projectInfo.expectedRecruitmentDuration())
        );
        ProjectEntity save = projectService.save(updatedProjectEntity);
        return ProjectInfo.from(save);
    }

    @Transactional
    public Boolean deleteProject(String projectName) {
        ProjectEntity projectEntity = projectService.getByProjectName(projectName);
        projectService.delete(projectEntity);
        return true;
    }
}
