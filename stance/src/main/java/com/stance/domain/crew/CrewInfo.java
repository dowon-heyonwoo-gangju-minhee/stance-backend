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


    public static List<CrewInfoEntity> toEntity(List<CrewInfo> crewInfos) {
        List<CrewInfoEntity> crewInfoEntities = new ArrayList<>();
        for (CrewInfo crewInfo : crewInfos) {
            crewInfoEntities.add(new CrewInfoEntity(crewInfo.githubName(), crewInfo.githubEmail(), crewInfo.nickName(), crewInfo.position(), Tools.toEntity(crewInfo.tools()), crewInfo.years()));
        }
        return crewInfoEntities;
    }

    public void validate() {
        if (githubName.isEmpty()) {
            throw new IllegalArgumentException("깃허브 이름이 비어있습니다.");
        }
        if (githubEmail.isEmpty()) {
            throw new IllegalArgumentException("깃허브 이메일이 비어있습니다.");
        }
        if (nickName.isEmpty()) {
            throw new IllegalArgumentException("닉네임이 비어있습니다.");
        }
        if (position.isEmpty()) {
            throw new IllegalArgumentException("포지션이 비어있습니다.");
        }
        if (tools.isEmpty()) {
            throw new IllegalArgumentException("툴이 비어있습니다.");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("경력은 0보다 커야 합니다.");
        }
    }
}
