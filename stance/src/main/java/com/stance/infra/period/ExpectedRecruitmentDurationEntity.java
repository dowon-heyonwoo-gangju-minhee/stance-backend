package com.stance.infra.period;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "expected_recruitment_duration")
public class ExpectedRecruitmentDurationEntity {
    @Id
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ExpectedRecruitmentDurationEntity(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        this.startDate = localDateTime;
        this.endDate = localDateTime1;
    }

    public ExpectedRecruitmentDurationEntity() {

    }
}
