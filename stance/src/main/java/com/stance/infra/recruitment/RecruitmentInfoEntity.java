package com.stance.infra.recruitment;

import com.stance.infra.tools.ToolsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class RecruitmentInfoEntity {
    @Id
    private Long id;

    @Getter
    private String position;

    @Getter
    @OneToMany
    private List<ToolsEntity> tools;

    @Getter
    private Long years;

    public RecruitmentInfoEntity(String position, List<ToolsEntity> entity, Long years) {
        this.position = position;
        this.tools = entity;
        this.years = years;
    }

    public RecruitmentInfoEntity() {

    }
}
