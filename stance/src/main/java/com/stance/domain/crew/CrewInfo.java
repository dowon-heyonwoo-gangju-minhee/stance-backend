package com.stance.domain.crew;

import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;

import java.util.ArrayList;
import java.util.List;

public record CrewInfo(String githubName,
                       String githubEmail,
                       String nickName,
                       String position,
                       List<Tools> tools,
                       Long years
){

    public static List<CrewInfo> from(List<CrewInfoEntity> crewInfoEntity) {
        List<CrewInfo> crewInfos = new ArrayList<>();
        for (CrewInfoEntity entity : crewInfoEntity) {
            crewInfos.add(new CrewInfo(entity.getGithubName(), entity.getGithubEmail(), entity.getNickName(), entity.getPosition(), Tools.from(entity.getTools()), entity.getYears()));
        }
        return crewInfos;

    }
    public static CrewInfo from(CrewInfoEntity entity) {
        return new CrewInfo(entity.getGithubName(), entity.getGithubEmail(), entity.getNickName(), entity.getPosition(), Tools.from(entity.getTools()), entity.getYears());
    }
    public static CrewInfoEntity toEntity(CrewInfo crewInfo) {
        return new CrewInfoEntity(crewInfo.githubName(), crewInfo.githubEmail(), crewInfo.nickName(), crewInfo.position(), Tools.toEntity(crewInfo.tools()), crewInfo.years());
    }

    public static List<CrewInfoEntity> toEntities(List<CrewInfo> crewInfos) {
        List<CrewInfoEntity> crewInfoEntities = new ArrayList<>();
        for (CrewInfo crewInfo : crewInfos) {
            crewInfoEntities.add(new CrewInfoEntity(crewInfo.githubName(), crewInfo.githubEmail(), crewInfo.nickName(), crewInfo.position(), Tools.toEntity(crewInfo.tools()), crewInfo.years()));
        }
        return crewInfoEntities;
    }

}
