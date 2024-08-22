package com.stance.application;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.CrewMapper;
import com.stance.domain.crew.RecruitmentInfo;
import com.stance.domain.period.ExpectedProjectDuration;
import com.stance.domain.period.ExpectedRecruitmentDuration;
import com.stance.domain.project.ProjectCommand;
import com.stance.domain.project.ProjectInfo;
import com.stance.domain.project.ProjectRepository;
import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.project.ProjectEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectInfo> getProjects() {
        List<ProjectEntity> projectEntities = projectRepository.getAll();
        return ProjectInfo.from(projectEntities);
    }

    @Transactional
    public ProjectInfo enrollProject(ProjectCommand.Apply command) {
        String projectName = command.projectName();
        CrewInfo crewInfo = command.crewInfo();
        CrewInfoEntity entity = CrewMapper.toEntity(crewInfo);
        ProjectEntity projectInfo = projectRepository.getByProjectName(projectName);
        projectInfo.addCrewInfo(entity);
        ProjectEntity savedProjectEntity = projectRepository.save(projectInfo);
        return ProjectInfo.from(savedProjectEntity);
    }

    @Transactional
    public ProjectInfo createProject(ProjectInfo project) {
        ProjectEntity projectEntity = new ProjectEntity(
                project.projectName(),
                project.description(),
                CrewInfo.toEntity(project.crewInfo()),
                RecruitmentInfo.toEntity(project.recruitmentInfoEntity()),
                ExpectedProjectDuration.toEntity(project.expectedProjectDurationEntity()),
                ExpectedRecruitmentDuration.toEntity(project.expectedRecruitmentDurationEntity())
        );
        ProjectEntity savedProjectEntity = projectRepository.save(projectEntity);
        return ProjectInfo.from(savedProjectEntity);
    }

    @Transactional
    public ProjectInfo patchProject(ProjectCommand.Patch patch) {
        String projectName = patch.projectName();
        ProjectInfo projectInfo = patch.projectInfo();
        ProjectEntity projectEntity = projectRepository.getByProjectName(projectName);
        Long id = projectEntity.getId();
        ProjectEntity updatedProjectEntity = new ProjectEntity(
                id,
                projectInfo.projectName(),
                projectInfo.description(),
                CrewInfo.toEntity(projectInfo.crewInfo()),
                RecruitmentInfo.toEntity(projectInfo.recruitmentInfoEntity()),
                ExpectedProjectDuration.toEntity(projectInfo.expectedProjectDurationEntity()),
                ExpectedRecruitmentDuration.toEntity(projectInfo.expectedRecruitmentDurationEntity())
        );
        ProjectEntity save = projectRepository.save(updatedProjectEntity);
        return ProjectInfo.from(save);
    }

    public Boolean deleteProject(String projectName) {
        ProjectEntity projectEntity = projectRepository.getByProjectName(projectName);
        projectRepository.delete(projectEntity);
        return true;
    }
}
