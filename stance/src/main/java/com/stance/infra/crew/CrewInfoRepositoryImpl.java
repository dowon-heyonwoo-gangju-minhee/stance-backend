package com.stance.infra.crew;

import com.stance.domain.crew.CrewInfoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CrewInfoRepositoryImpl implements CrewInfoRepository {
    private final CrewJPARepository crewJPARepository;

    public CrewInfoRepositoryImpl(CrewJPARepository crewJPARepository) {
        this.crewJPARepository = crewJPARepository;
    }

    @Override
    public CrewInfoEntity save(CrewInfoEntity crewInfoEntity) {
        return crewJPARepository.save(crewInfoEntity);

    }

    @Override
    public CrewInfoEntity getByCrewEmail(String crewEmail) {
        return crewJPARepository.findByCrewEmail(crewEmail);
    }
}
