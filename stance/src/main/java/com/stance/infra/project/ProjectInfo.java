package com.stance.infra.project;

import com.stance.interfaces.ProjectDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {

    public static List<ProjectInfo.Details> from(List<ProjectEntity> projectEntities) {
        List<ProjectInfo.Details> projectInfos = new ArrayList<>();
        for(ProjectEntity projectEntity : projectEntities){
            List<CrewInfoEntity> crewInfoEntity = projectEntity.getCrewInfoEntity();
            List<RecruitmentInfo> recruitmentInfo = projectEntity.getRecruitmentInfo();
            ExpectedProjectDuration expectedProjectDuration = projectEntity.getExpectedProjectDuration();
            ExpectedRecruitmentDuration expectedRecruitmentDuration = projectEntity.getExpectedRecruitmentDuration();
            Details info = getDetails(projectEntity, expectedRecruitmentDuration, expectedProjectDuration);
            projectInfos.add(info);
        }
        return projectInfos;
    }

    private static Details getDetails(ProjectEntity projectEntity, ExpectedRecruitmentDuration expectedRecruitmentDuration, ExpectedProjectDuration expectedProjectDuration) {
        ProjectDto.DurationInfo recruitmentDurationInfo = new ProjectDto.DurationInfo(expectedRecruitmentDuration.getStartDate(), expectedRecruitmentDuration.getEndDate());
        ProjectDto.DurationInfo durationInfo = new ProjectDto.DurationInfo(expectedProjectDuration.getStartDate(), expectedProjectDuration.getEndDate());
        return new Details(projectEntity.getProjectName(),
        projectEntity.getDescription(),
        projectEntity.getCrewInfoEntity(),
        projectEntity.getRecruitmentInfo(),
        durationInfo,
        recruitmentDurationInfo);
    }

    public record Details(String projectName,
                          String description,
                          List<CrewInfoEntity> crewInfoEntity,
                          List<RecruitmentInfo> recruitmentInfo,
                          ProjectDto.DurationInfo expectedProjectDuration,
                          ProjectDto.DurationInfo expectedRecruitmentDuration){

        public void validate(){
            if(projectName.isEmpty()){
                throw new IllegalArgumentException("프로젝트 이름이 비어있습니다.");
            }
            if(description.isEmpty()){
                throw new IllegalArgumentException("프로젝트 설명이 비어있습니다.");
            }
            if(crewInfoEntity.isEmpty()){
                throw new IllegalArgumentException("크루 정보가 비어있습니다.");
            }
            if(recruitmentInfo.isEmpty()){
                throw new IllegalArgumentException("모집 정보가 비어있습니다.");
            }
            expectedProjectDuration.validate();
            expectedRecruitmentDuration.validate();
        }
    }
}
