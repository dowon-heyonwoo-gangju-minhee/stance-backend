package com.stance.infra.tools;

import com.stance.infra.crew.CrewInfoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
@Getter
@Entity
public class ToolsEntity {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "crew_info_id")
    private CrewInfoEntity crewInfoEntity;

    public ToolsEntity(String stackName) {
        this.name = stackName;
    }

    public ToolsEntity() {

    }
}
