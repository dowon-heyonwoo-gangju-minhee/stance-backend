package com.stance.infra.crew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CrewJPARepository extends JpaRepository<CrewInfoEntity,Long> {
    @Query("SELECT c FROM CrewInfoEntity c WHERE c.githubEmail = ?1")
    CrewInfoEntity findByCrewEmail(String crewEmail);
}
