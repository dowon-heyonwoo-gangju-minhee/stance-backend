package com.stance.domain.crew;

import com.stance.domain.like.ProjectLikeInfo;
import com.stance.domain.tools.Tools;
import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.like.CrewLikeInfo;

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
    public static CrewInfoEntity to(CrewInfo crewInfo) {
        return new CrewInfoEntity(crewInfo.githubName(), crewInfo.githubEmail(), crewInfo.nickName(), crewInfo.position(), Tools.toEntity(crewInfo.tools()), crewInfo.years());
    }

    public static List<CrewInfoEntity> to(List<CrewInfo> crewInfos) {
        List<CrewInfoEntity> crewInfoEntities = new ArrayList<>();
        for (CrewInfo crewInfo : crewInfos) {
            crewInfoEntities.add(new CrewInfoEntity(crewInfo.githubName(), crewInfo.githubEmail(), crewInfo.nickName(), crewInfo.position(), Tools.toEntity(crewInfo.tools()), crewInfo.years()));
        }
        return crewInfoEntities;
    }



    public static CrewInfo from(ProjectLikeInfo projectLikeInfo) {
        return new CrewInfo(projectLikeInfo.crewInfo().githubName(), projectLikeInfo.crewInfo().githubEmail(), projectLikeInfo.crewInfo().nickName(), projectLikeInfo.crewInfo().position(), projectLikeInfo.crewInfo().tools(), projectLikeInfo.crewInfo().years());
    }

    public static CrewInfo from(CrewLikeInfo crewLikeInfo) {
        return new CrewInfo(crewLikeInfo.liker().githubName(), crewLikeInfo.liker().githubEmail(), crewLikeInfo.liker().nickName(), crewLikeInfo.liker().position(), crewLikeInfo.liker().tools(), crewLikeInfo.liker().years());
    }
}
