package com.stance.infra.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
@Getter
@Entity
public class Tools {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "crew_info_id")
    private CrewInfoEntity crewInfoEntity;
}
