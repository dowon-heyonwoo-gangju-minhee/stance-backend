package com.stance.infra.crew;

import com.stance.domain.crew.CrewRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CrewRepositoryImpl implements CrewRepository {
    private final CrewJPARepository crewJPARepository;

    public CrewRepositoryImpl(CrewJPARepository crewJPARepository) {
        this.crewJPARepository = crewJPARepository;
    }

    @Override
    public CrewInfoEntity save(CrewInfoEntity crewInfoEntity) {
        return crewJPARepository.save(crewInfoEntity);

    }
}
