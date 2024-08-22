package com.stance.domain.project;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.RecruitmentInfo;
import com.stance.domain.period.ExpectedProjectDuration;
import com.stance.domain.period.ExpectedRecruitmentDuration;
import com.stance.infra.project.*;

import java.util.ArrayList;
import java.util.List;

public record ProjectInfo(
        String projectName, String description,
        List<CrewInfo> crewInfo, List<RecruitmentInfo> recruitmentInfo,
        ExpectedProjectDuration expectedProjectDuration, ExpectedRecruitmentDuration expectedRecruitmentDuration
) {

    public static List<ProjectInfo> from(List<ProjectEntity> projectEntities) {
        List<ProjectInfo> projectInfos = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntities) {
            projectInfos.add(from(projectEntity));
        }
        return projectInfos;
    }
    public static ProjectInfo from(ProjectEntity projectEntity) {
        return new ProjectInfo(
                projectEntity.getProjectName(),
                projectEntity.getDescription(),
                CrewInfo.from(projectEntity.getCrewInfoEntity()),
                RecruitmentInfo.from(projectEntity.getRecruitmentInfoEntity()),
                ExpectedProjectDuration.from(projectEntity.getExpectedProjectDurationEntity()),
                ExpectedRecruitmentDuration.from(projectEntity.getExpectedRecruitmentDurationEntity())
        );

    }


    public void validate() {
        if (projectName.isEmpty()) {
            throw new IllegalArgumentException("프로젝트 이름이 비어있습니다.");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("프로젝트 설명이 비어있습니다.");
        }
        if (crewInfo.isEmpty()) {
            throw new IllegalArgumentException("크루 정보가 비어있습니다.");
        }
        if (recruitmentInfo.isEmpty()) {
            throw new IllegalArgumentException("모집 정보가 비어있습니다.");
        }
        if (expectedProjectDuration == null) {
            throw new IllegalArgumentException("예상 프로젝트 기간이 비어있습니다.");
        }
        if (expectedRecruitmentDuration == null) {
            throw new IllegalArgumentException("예상 모집 기간이 비어있습니다.");
        }
    }
}
