package com.stance.infra.like;

import com.stance.domain.crew.CrewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrewLikeJPArepository extends JpaRepository<CrewLikeEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(cl) > 0 THEN TRUE ELSE FALSE END FROM CrewLikeEntity cl WHERE cl.liker = :liker AND cl.likedCrew = :likedCrew")
    Boolean findByLikerAndLikedCrew(CrewInfo liker, CrewInfo likedCrew);

    @Query("SELECT cl FROM CrewLikeEntity cl WHERE cl.liker.githubEmail = :likerEmail")
    List<CrewLikeEntity> findByLikerId(String likerEmail);
}
