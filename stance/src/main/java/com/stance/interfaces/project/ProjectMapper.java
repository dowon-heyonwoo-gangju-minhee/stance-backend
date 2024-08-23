package com.stance.interfaces.project;


import com.stance.domain.project.ProjectCommand;
import com.stance.domain.project.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {
    public static List<ProjectDto.ProjectResponse> toGetResponse(List<ProjectInfo> projects) {
        List<ProjectDto.ProjectResponse> projectResponses = new ArrayList<>();
        for (ProjectInfo project : projects) {
            projectResponses.add(new ProjectDto.ProjectResponse(
                    project
            ));
        }
        return projectResponses;
    }

    public static ProjectDto.EnrollResponse toPostResponse(ProjectInfo projectInfo) {
        return new ProjectDto.EnrollResponse(projectInfo);
    }

    public static ProjectCommand.Apply toEnroll(ProjectDto.EnrollRequest request) {
        return new ProjectCommand.Apply(request.projectName(), request.crewInfo());
    }

    public static ProjectDto.ProjectResponse toPatchResponse(ProjectInfo projectInfo) {
        return new ProjectDto.ProjectResponse(
                projectInfo
        );
    }

    public static ProjectCommand.Patch toPatch(String projectName, ProjectDto.ModifyRequest request) {
        return new ProjectCommand.Patch(projectName, request.projectInfo());
    }

    public static ProjectDto.DeleteResponse toDeleteResponse(Boolean aBoolean, String projectName) {
        return new ProjectDto.DeleteResponse("ProjectName", "Deleted");
    }

    public static ProjectCommand.Create toCreate(ProjectInfo project) {
        return new ProjectCommand.Create(project);
    }
}
