package com.stance.domain.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.infra.like.CrewLikeEntity;
import com.stance.infra.like.CrewLikeInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrewLikeService {
    private final CrewLikeRepository crewLikeRepository;

    public CrewLikeService(CrewLikeRepository crewLikeRepository) {
        this.crewLikeRepository = crewLikeRepository;
    }

    public Boolean findByLikerAndLikedCrew(CrewInfo liker, CrewInfo likedCrew) {
        return crewLikeRepository.findByLikerAndLikedCrew(liker, likedCrew);
    }

    public void save(CrewLikeEntity like) {
        crewLikeRepository.save(like);
    }

    public List<CrewLikeInfo> getByLikerId(String likerEmail) {
        List<CrewLikeEntity> byLikerId = crewLikeRepository.getByLikerId(likerEmail);
        return CrewLikeInfo.from(byLikerId);
    }
}
