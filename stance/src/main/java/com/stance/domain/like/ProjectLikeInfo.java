package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import com.stance.infra.like.ProjectLikeEntity;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectLikeInfo(ProjectInfo projectInfo,
                              CrewInfo crewInfo,
                              LocalDateTime likedAt
) {
    public static ProjectLikeEntity to(ProjectLikeInfo projectLikeInfo) {
        return new ProjectLikeEntity(ProjectInfo.toEntity(projectLikeInfo.projectInfo()),
                CrewInfo.to(projectLikeInfo.crewInfo()),
                projectLikeInfo.likedAt());
    }

    public static ProjectLikeInfo from(ProjectLikeEntity projectLikeEntity) {
        return new ProjectLikeInfo(ProjectInfo.from(projectLikeEntity.getProject()),
                CrewInfo.from(projectLikeEntity.getCrew()),
                projectLikeEntity.getLikedAt());
    }

    public static List<ProjectLikeEntity> to(List<ProjectLikeInfo> projectLikeInfos) {
        return projectLikeInfos.stream().map(ProjectLikeInfo::to).toList();
    }

    public static List<ProjectLikeInfo> from(List<ProjectLikeEntity> projectLikeEntities) {
        return projectLikeEntities.stream().map(ProjectLikeInfo::from).toList();
    }
}


