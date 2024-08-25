package com.stance.interfaces.project;


import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import com.stance.domain.tools.Tools;

import java.time.LocalDateTime;
import java.util.List;

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

    public record CompleteResponse(String projectName, String message) {
    }

    public record CompleteRequest(String projectName, String message) {
        public void validate(){
            if(projectName == null || projectName.isBlank()){
                throw new IllegalArgumentException("projectName is null or empty");
            }
            if(message == null || message.isBlank()){
                throw new IllegalArgumentException("message is null or empty");
            }
        }
    }

    public record FilterRequest(List<Tools> tools) {
        public void validate(){
            if(tools == null || tools.isEmpty()){
                throw new IllegalArgumentException("tools is null or empty");
            }
        }
    }

    public record DateFilterRequest(Long durationMonth ) {
        public void validate(){
            if(durationMonth == null || durationMonth <= 0){
                throw new IllegalArgumentException("durationMonth is null or empty");
            }
        }
    }

    public record PositionFilterRequest(String position) {
        public void validate(){
            if(position == null || position.isBlank()){
                throw new IllegalArgumentException("position is null or empty");
            }
        }
    }
}
