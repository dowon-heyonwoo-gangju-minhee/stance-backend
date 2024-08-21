package com.stance.infra.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ExpectedProjectDuration {
    @Id
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
