package com.stance.domain.period;

import com.stance.infra.period.ExpectedRecruitmentDurationEntity;

import java.time.LocalDateTime;

public record ExpectedRecruitmentDuration(LocalDateTime startDate,
                                           LocalDateTime endDate) {
    public static ExpectedRecruitmentDuration from(ExpectedRecruitmentDurationEntity expectedRecruitmentDurationEntity) {
        return new ExpectedRecruitmentDuration(expectedRecruitmentDurationEntity.getStartDate(), expectedRecruitmentDurationEntity.getEndDate());
    }

    public static ExpectedRecruitmentDurationEntity toEntity(ExpectedRecruitmentDuration expectedRecruitmentDuration) {
        return new ExpectedRecruitmentDurationEntity(expectedRecruitmentDuration.startDate(), expectedRecruitmentDuration.endDate());
    }
}
