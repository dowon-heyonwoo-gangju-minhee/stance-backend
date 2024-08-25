package com.stance.domain.like;

public class LikeCommand {
    public record Like(String projectName, String crewEmail) {
    }

}
