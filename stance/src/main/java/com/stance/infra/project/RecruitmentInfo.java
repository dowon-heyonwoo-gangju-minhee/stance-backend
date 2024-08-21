package com.stance.infra.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
@Getter
@Entity
public class RecruitmentInfo {
    @Id
    private Long id;


}
