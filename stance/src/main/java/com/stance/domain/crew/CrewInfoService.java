package com.stance.domain.crew;

import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CrewInfoService {
    private final CrewInfoRepository crewInfoRepository;

    public CrewInfoService(CrewInfoRepository crewInfoRepository) {
        this.crewInfoRepository = crewInfoRepository;
    }

    @Transactional
    public CrewInfo createCrew(CrewCommand.Create command) {
        CrewInfoEntity crewInfoEntity = new CrewInfoEntity(
                command.githubName(),
                command.githubEmail(),
                command.nickName(),
                command.position(),
                Tools.toEntity(command.tools()),
                command.years()
        );

        CrewInfoEntity save = crewInfoRepository.save(crewInfoEntity);
        return CrewInfo.from(save);
    }

    @Transactional
    public CrewInfo getCrew(String crewEmail) {
        CrewInfoEntity crewInfoEntity = crewInfoRepository.getByCrewEmail(crewEmail);
        return CrewInfo.from(crewInfoEntity);

    }
}
