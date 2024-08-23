package com.stance.interfaces.project;


import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;

public class ProjectDto {

    public record EnrollRequest(String projectName, String crewEmail){
        public void validate(){
            if(projectName == null || projectName.isBlank()){
                throw new IllegalArgumentException("projectName is null or empty");
            }
            if(crewEmail == null || crewEmail.isBlank()){
                throw new IllegalArgumentException("crewEmail is null or empty");
            }
        }
    }
    public record CreationRequest(ProjectInfo project){
        public void validate(){
            project.validate();
        }
    }


    public record ProjectResponse(ProjectInfo projectInfo) {
    }

    public record EnrollResponse(ProjectInfo projectInfo) {
    }

    public record CreationResponse(ProjectInfo projectInfo) {
    }

    public record DeleteResponse(String projectName, String message) {
    }

    public record ModifyRequest(ProjectInfo projectInfo) {
    }
}
