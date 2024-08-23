package com.stance.domain.crew;

import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CrewService {
    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
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

        CrewInfoEntity save = crewRepository.save(crewInfoEntity);
        return CrewInfo.from(save);
    }

    @Transactional
    public CrewInfo getCrew(String crewEmail) {
        CrewInfoEntity crewInfoEntity = crewRepository.getByCrewEmail(crewEmail);
        return CrewInfo.from(crewInfoEntity);

    }
}
