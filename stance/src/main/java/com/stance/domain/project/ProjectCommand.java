package com.stance.domain.project;

import com.stance.domain.crew.CrewInfo;
import com.stance.interfaces.project.ProjectDto;

public class ProjectCommand {

    public record Create(CrewInfo crewInfo, ProjectInfo projectInfo){

    }
    public record Apply(String projectName, CrewInfo crewInfo){

    }

    public record Patch(String projectName, ProjectInfo projectInfo){

    }
}
