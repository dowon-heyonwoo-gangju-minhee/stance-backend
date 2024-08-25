package com.stance.infra.like;

import com.stance.domain.crew.CrewInfo;
import com.stance.domain.like.CrewLikeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrewLikeRepositoryImpl implements CrewLikeRepository {
    private final CrewLikeJPArepository crewLikeJPArepository;

    public CrewLikeRepositoryImpl(CrewLikeJPArepository crewLikeJPArepository) {
        this.crewLikeJPArepository = crewLikeJPArepository;
    }


    @Override
    public Boolean findByLikerAndLikedCrew(CrewInfo liker, CrewInfo likedCrew) {
        return crewLikeJPArepository.findByLikerAndLikedCrew(liker, likedCrew);
        
    }

    @Override
    public void save(CrewLikeEntity like) {
        crewLikeJPArepository.save(like);
    }

    @Override
    public List<CrewLikeEntity> getByLikerId(String likerEmail) {
        return crewLikeJPArepository.findByLikerId(likerEmail);
    }
}
