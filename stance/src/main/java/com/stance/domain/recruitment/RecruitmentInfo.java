package com.stance.domain.recruitment;

import com.stance.domain.tools.Tools;
import com.stance.infra.recruitment.RecruitmentInfoEntity;

import java.util.List;

public record RecruitmentInfo(String position,
                              List<Tools> tools,
                              Long years) {
    public static List<RecruitmentInfo> from(List<RecruitmentInfoEntity> recruitmentInfoEntity) {
        List<RecruitmentInfo> recruitmentInfos = new java.util.ArrayList<>();
        for (RecruitmentInfoEntity entity : recruitmentInfoEntity) {
            recruitmentInfos.add(new RecruitmentInfo(entity.getPosition(), Tools.from(entity.getTools()), entity.getYears()));
        }
        return recruitmentInfos;
    }

    public static List<RecruitmentInfoEntity> toEntity(List<RecruitmentInfo> recruitmentInfos) {
        List<RecruitmentInfoEntity> recruitmentInfoEntities = new java.util.ArrayList<>();
        for (RecruitmentInfo recruitmentInfo : recruitmentInfos) {
            recruitmentInfoEntities.add(new RecruitmentInfoEntity(recruitmentInfo.position(), Tools.toEntity(recruitmentInfo.tools()), recruitmentInfo.years()));
        }
        return recruitmentInfoEntities;
    }
}
