package com.stance.domain.crew;

import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.tools.ToolsMapper;
import org.springframework.stereotype.Component;

@Component
public class CrewService {
    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public CrewInfo createCrew(CrewCommand.Create command) {
        CrewInfoEntity crewInfoEntity = new CrewInfoEntity(
                command.githubName(),
                command.githubEmail(),
                command.nickName(),
                command.position(),
                ToolsMapper.toEntities(command.tools()),
                command.years()
        );

        CrewInfoEntity save = crewRepository.save(crewInfoEntity);
        return CrewInfo.from(save);
    }
}
