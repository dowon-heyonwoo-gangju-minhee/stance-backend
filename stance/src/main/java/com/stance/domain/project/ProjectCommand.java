package com.stance.domain.project;

import com.stance.domain.crew.CrewInfo;

public class ProjectCommand {

    public record Create( ProjectInfo projectInfo){

    }
    public record Apply(String projectName, CrewInfo crewInfo){

    }

    public record Patch(String projectName, ProjectInfo projectInfo){

    }
}
