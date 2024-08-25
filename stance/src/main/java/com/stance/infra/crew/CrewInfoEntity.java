package com.stance.infra.crew;

import com.stance.infra.like.CrewLikeEntity;
import com.stance.infra.like.ProjectLikeEntity;
import com.stance.infra.membership.MembershipEntity;
import com.stance.infra.project.ProjectEntity;
import com.stance.infra.tools.ToolsEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="crew_info")
public class CrewInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String githubName;

    @OneToMany(mappedBy = "crew")
    private List<ProjectLikeEntity> likedProjects = new ArrayList<>();

    @OneToMany(mappedBy = "liker")
    private List<CrewLikeEntity> likedCrews = new ArrayList<>();

    @Getter
    private String githubEmail;

    @Getter
    private String nickName;


    private String position;

    @OneToMany
    @JoinColumn(name = "crew_info_id")
    private List<ToolsEntity> tools;

    @OneToMany(mappedBy = "crew")
    private List<MembershipEntity> memberships;

    private Long years;

    private Long involvements = 0L;

    public CrewInfoEntity(String githubName, String githubEmail, String nickName, String position, List<ToolsEntity> tools, Long years) {
        this.githubName = githubName;
        this.githubEmail = githubEmail;
        this.nickName = nickName;
        this.position = position;
        this.tools = tools;
        this.years = years;
    }

    public CrewInfoEntity() {

    }

    @Version
    private Long version;


    @Transactional
    public void incrementProjectParticipation() {
        this.involvements++;
    }
    public void addLikedProject(ProjectEntity project) {
        ProjectLikeEntity like = new ProjectLikeEntity(project, this, LocalDateTime.now());
        this.likedProjects.add(like);
    }

    public void addLikedCrew(CrewInfoEntity likedCrew) {
        CrewLikeEntity like = new CrewLikeEntity(this, likedCrew, LocalDateTime.now());
        this.likedCrews.add(like);
    }
}
