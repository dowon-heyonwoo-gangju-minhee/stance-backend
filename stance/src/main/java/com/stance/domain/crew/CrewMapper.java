package com.stance.domain.crew;

import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.tools.ToolsMapper;

public class CrewMapper {
    public static CrewInfoEntity toEntity(CrewInfo create) {
        return new CrewInfoEntity(
                create.githubName(),
                create.githubEmail(),
                create.nickName(),
                create.position(),
                ToolsMapper.toEntities(create.tools()),
                create.years()
        );
    }

}
