package com.stance.infra.like;

import com.stance.domain.crew.CrewInfo;

import java.util.List;
import java.util.stream.Collectors;

public record CrewLikeInfo(CrewInfo liker, CrewInfo likedCrew) {
    public static CrewLikeEntity to(CrewLikeInfo crewLikeInfo) {
        return new CrewLikeEntity(CrewInfo.to(crewLikeInfo.liker()),
                CrewInfo.to(crewLikeInfo.likedCrew()));
    }

    public static CrewLikeInfo from(CrewLikeEntity crewLikeEntity) {
        return new CrewLikeInfo(CrewInfo.from(crewLikeEntity.getLiker()),
                CrewInfo.from(crewLikeEntity.getLikedCrew()));
    }
    public static List<CrewLikeInfo> from(List<CrewLikeEntity> crewLikeEntities) {
        return crewLikeEntities.stream()
                .map(CrewLikeInfo::from)
                .collect(Collectors.toList());
    }
}
