package com.stance.interfaces.crew;

import com.stance.domain.crew.CrewCommand;
import com.stance.domain.crew.CrewInfo;

public class CrewMapper {
    public static CrewDto.CreateCrewResponse toResponse(CrewInfo crewInfo){
        return new CrewDto.CreateCrewResponse(crewInfo.nickName(),
                crewInfo.position(),
                crewInfo.tools(),
                crewInfo.years());

    }

    public static CrewCommand.Create toCreate(CrewDto.CreateCrewRequest request) {
        return new CrewCommand.Create(
                request.githubName(),
                request.githubEmail(),
                request.nickName(),
                request.position(),
                request.tools(),
                request.years()
        );

    }
}
