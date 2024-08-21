package com.stance.interfaces;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProjectDto {

    public record EnrollInfo(String nickName,
                            String position,
                             List<Tools> tools,
                             String years,
                             Integer involvements){
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
            if(years.isEmpty()){
                throw new IllegalArgumentException("경력이 비어있습니다.");
            }
            if(involvements <= 0){
                throw new IllegalArgumentException("참여횟수는 0보다 커야 합니다.");
            }
        }
    }
    public record ProjectEnrollRequest(EnrollInfo request){
        public void validate(){
            request.validate();
        }
    }
    public record ProjectCreationRequest(EnrollInfo common, ProjectInfo project){
        public void validate(){
            common.validate();
            project.validate();
        }
    }
    public record Tools(String stackName){

    }
    public record CrewInfo(String githubName,
                           String githubEmail,
                           String nickName,
                           String position,
                           List<Tools> tools,
                           Long years
    ){

    }
    public record RecruitmentInfo(String position,
                                  List<Tools> tools,
                                  Long years

    ){
    }

    public record ProjectInfo(String projectName,
                              String description,
                              List<CrewInfo> crewInfo,
                              List<RecruitmentInfo> recruitmentInfo,
                              DurationInfo expectedProjectDuration,
                              DurationInfo expectedRecruitmentDuration){
        public void validate(){
            if(projectName.isEmpty()){
                throw new IllegalArgumentException("프로젝트 이름이 비어있습니다.");
            }
            if(description.isEmpty()){
                throw new IllegalArgumentException("프로젝트 설명이 비어있습니다.");
            }
            if(crewInfo.isEmpty()){
                throw new IllegalArgumentException("크루 정보가 비어있습니다.");
            }
            if(recruitmentInfo.isEmpty()){
                throw new IllegalArgumentException("모집 정보가 비어있습니다.");
            }
            expectedProjectDuration.validate();
            expectedRecruitmentDuration.validate();
        }
    }
    public record DurationInfo(LocalDateTime startDate, LocalDateTime endDate){
        public void validate(){
            if(startDate.isAfter(endDate)){
                throw new IllegalArgumentException("시작일이 종료일보다 늦습니다.");
            }
        }
    }


    public record ProjectResponse(ProjectInfo projectInfo) {
    }

    public record ProjectEnrollResponse(EnrollInfo enrollInfo) {
    }

    public record ProjectCreationResponse(ProjectInfo projectInfo) {
    }

    public record DeleteResponse(String projectName, String message) {
    }
}
