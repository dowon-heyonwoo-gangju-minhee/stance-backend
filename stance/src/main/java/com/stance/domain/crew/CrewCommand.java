package com.stance.domain.crew;

import com.stance.domain.tools.Tools;

import java.util.List;

public class CrewCommand {
    public record Create (
            String githubName,
            String githubEmail,
            String nickName
            , String position
            , List<Tools> tools
            , Long years) {
    }
}
