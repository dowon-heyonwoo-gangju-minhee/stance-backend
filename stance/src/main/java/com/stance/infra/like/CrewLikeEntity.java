package com.stance.infra.like;

import com.stance.infra.crew.CrewInfoEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Entity
@Table(name = "crew_like")
public class CrewLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "liker_id")
    private CrewInfoEntity liker;

    @ManyToOne
    @JoinColumn(name = "liked_crew_id")
    private CrewInfoEntity likedCrew;

    private LocalDateTime likedAt;

    public CrewLikeEntity(CrewInfoEntity crewInfoEntity, CrewInfoEntity likedCrew) {
        this.liker = crewInfoEntity;
        this.likedCrew = likedCrew;
        this.likedAt = LocalDateTime.now();
    }

    public CrewLikeEntity() {

    }

    public CrewLikeEntity(CrewInfoEntity crewInfoEntity, CrewInfoEntity likedCrew, LocalDateTime now) {
        this.liker = crewInfoEntity;
        this.likedCrew = likedCrew;
        this.likedAt = now;
    }
}