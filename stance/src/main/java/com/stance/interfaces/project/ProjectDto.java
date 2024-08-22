package com.stance.interfaces.project;


import com.stance.domain.crew.CrewInfo;
import com.stance.domain.project.ProjectInfo;
import com.stance.domain.tools.Tools;

import java.util.List;

public class ProjectDto {

    public record EnrollInfo(String nickName,
                            String position,
                             List<Tools> tools,
                             Long years,
                             Long involvements){
        public void validate(){
            if(nickName.isEmpty()){
                throw new IllegalArgumentException("닉네임이 비어있습니다.");
            }
            if(position.isEmpty()){
                throw new IllegalArgumentException("포지션이 비어있습니다.");
            }
            if(tools.isEmpty()){
                throw new IllegalArgumentException("툴이 비어있습니다.");
            }
            if(years <= 0){
                throw new IllegalArgumentException("경력은 0보다 커야 합니다.");
            }
            if(involvements <= 0){
                throw new IllegalArgumentException("참여횟수는 0보다 커야 합니다.");
            }
        }
    }
    public record EnrollRequest(String projectName, CrewInfo crewInfo){
        public void validate(){
            crewInfo.validate();
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
