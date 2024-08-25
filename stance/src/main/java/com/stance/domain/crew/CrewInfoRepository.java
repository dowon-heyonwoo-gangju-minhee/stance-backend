package com.stance.domain.crew;

import com.stance.infra.crew.CrewInfoEntity;

public interface CrewInfoRepository {
    CrewInfoEntity save(CrewInfoEntity crewInfoEntity);

    CrewInfoEntity getByCrewEmail(String crewEmail);
}
