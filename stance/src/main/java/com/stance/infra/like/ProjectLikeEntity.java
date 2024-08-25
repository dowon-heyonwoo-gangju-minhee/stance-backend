package com.stance.infra.like;

import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Entity
@Table(name = "project_like")
public class ProjectLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private CrewInfoEntity crew;

    private LocalDateTime likedAt;

    public ProjectLikeEntity(ProjectEntity projectEntity, CrewInfoEntity crew, LocalDateTime now) {
        this.project = projectEntity;
        this.crew = crew;
        this.likedAt = now;
    }

    public ProjectLikeEntity() {

    }

}