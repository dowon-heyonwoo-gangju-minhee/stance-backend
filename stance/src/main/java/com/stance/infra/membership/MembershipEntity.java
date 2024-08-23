package com.stance.infra.membership;

import com.stance.infra.crew.CrewInfoEntity;
import com.stance.infra.project.ProjectEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Entity
@Table(name = "membership")
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private CrewInfoEntity crew;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDateTime joinDate;

    public MembershipEntity(ProjectEntity projectEntity, CrewInfoEntity owner, MemberRole memberRole, LocalDateTime now) {
        this.project = projectEntity;
        this.crew = owner;
        this.role = memberRole;
        this.joinDate = now;
    }

    public MembershipEntity() {

    }
}

