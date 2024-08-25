package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.infra.like.CrewLikeEntity;

import java.util.List;

public interface CrewLikeRepository {
    Boolean findByLikerAndLikedCrew(CrewInfo liker, CrewInfo likedCrew);

    void save(CrewLikeEntity like);

    List<CrewLikeEntity> getByLikerId(String likerEmail);
}
