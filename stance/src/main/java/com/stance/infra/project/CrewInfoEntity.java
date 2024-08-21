package com.stance.infra.project;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name="crew_info")
public class CrewInfoEntity {
    @Id
    private Long id;

    private String name;

    private String position;

    @OneToMany
    @JoinColumn(name = "crew_info_id")
    private List<Tools> tools;

    private Integer years;

}
