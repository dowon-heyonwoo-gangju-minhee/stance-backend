package com.stance.infra.crew;

import com.stance.infra.tools.ToolsEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name="crew_info")
public class CrewInfoEntity {
    @Id
    private Long id;

    @Getter
    private String githubName;

    @Getter
    private String githubEmail;

    @Getter
    private String nickName;


    private String position;

    @OneToMany
    @JoinColumn(name = "crew_info_id")
    private List<ToolsEntity> tools;

    private Long years;

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
}
