package com.stance.infra.period;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "expected_project_duration")
public class ExpectedProjectDurationEntity {
    @Id
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public ExpectedProjectDurationEntity(LocalDateTime localDateTime, LocalDateTime localDateTime1) {
        this.startDate = localDateTime;
        this.endDate = localDateTime1;
    }

    public ExpectedProjectDurationEntity() {

    }
}
