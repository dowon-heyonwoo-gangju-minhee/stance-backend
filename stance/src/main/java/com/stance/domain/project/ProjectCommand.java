package com.stance.domain.project;

import com.stance.domain.tools.Tools;

import java.util.List;

public class ProjectCommand {

    public record Create( ProjectInfo projectInfo){

    }
    public record Apply(String projectName, String crewEmail){

    }

    public record Patch(String projectName, ProjectInfo projectInfo){

    }

    public record State(com.stance.interfaces.project.ProjectDto.CompleteRequest request) {
    }

    public record Filter(List<Tools> tools) {
    }

    public record DateFilter(Long durationMonths) {
    }

    public record PositionFilter(String position) {
    }
}
