package com.stance.domain.project;

public class ProjectCommand {

    public record Create( ProjectInfo projectInfo){

    }
    public record Apply(String projectName, String crewEmail){

    }

    public record Patch(String projectName, ProjectInfo projectInfo){

    }

    public record State(com.stance.interfaces.project.ProjectDto.CompleteRequest request) {
    }
}
