package com.stance.domain.period;

import com.stance.infra.period.ExpectedProjectDurationEntity;

import java.time.LocalDateTime;

public record ExpectedProjectDuration(LocalDateTime startDate,
                                       LocalDateTime endDate) {
    public static ExpectedProjectDuration from(ExpectedProjectDurationEntity expectedProjectDurationEntity) {
        return new ExpectedProjectDuration(expectedProjectDurationEntity.getStartDate(), expectedProjectDurationEntity.getEndDate());
    }

    public static ExpectedProjectDurationEntity toEntity(ExpectedProjectDuration expectedProjectDuration) {
        return new ExpectedProjectDurationEntity(expectedProjectDuration.startDate(), expectedProjectDuration.endDate());
    }
}
