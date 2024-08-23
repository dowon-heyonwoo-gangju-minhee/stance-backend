package com.stance.domain.crew;

import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;

public class CrewMapper {
    public static CrewInfoEntity toEntity(CrewInfo create) {
        return new CrewInfoEntity(
                create.githubName(),
                create.githubEmail(),
                create.nickName(),
                create.position(),
                Tools.toEntity(create.tools()),
                create.years()
        );
    }

}
