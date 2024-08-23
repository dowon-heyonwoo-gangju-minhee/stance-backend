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
        return new ProjectCommand.Apply(request.projectName(), request.crewEmail());
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
        if(aBoolean) {
            return new ProjectDto.DeleteResponse(projectName, "Deleted");
        }
        return new ProjectDto.DeleteResponse(projectName, "Not Deleted");
    }

    public static ProjectCommand.Create toCreate(ProjectInfo project) {
        return new ProjectCommand.Create(project);
    }

    public static ProjectDto.CompleteResponse toCompleteResponse(Boolean aBoolean, String projectName) {
        if(aBoolean) {
            return new ProjectDto.CompleteResponse(projectName, "Completed");
        }
        return new ProjectDto.CompleteResponse(projectName, "Not Completed");
    }

    public static ProjectCommand.State toState(ProjectDto.CompleteRequest request) {
        return new ProjectCommand.State(request);
    }
}
