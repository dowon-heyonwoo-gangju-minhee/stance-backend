package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.infra.like.CrewLikeEntity;

import java.time.LocalDateTime;

public record CrewLikeInfo(CrewInfo liker,
                           CrewInfo likedCrew,
                           LocalDateTime likedAt
                           ) {
    public static CrewLikeEntity to(CrewLikeInfo crewLikeInfo) {
        return new CrewLikeEntity(CrewInfo.to(crewLikeInfo.liker()),
                CrewInfo.to(crewLikeInfo.likedCrew())
           );
    }
    public static CrewLikeInfo from(CrewLikeEntity crewLikeEntity) {
        return new CrewLikeInfo(CrewInfo.from(crewLikeEntity.getLiker()),
                CrewInfo.from(crewLikeEntity.getLikedCrew()),
                crewLikeEntity.getLikedAt());
    }
}
