package com.stance.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
    private final String basePath = "/api/projects";
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    ProjectDto.DurationInfo timeInfo = new ProjectDto.DurationInfo(LocalDateTime.now(),LocalDateTime.now().plusDays(7));

    ProjectDto.ProjectInfo projectInfo = new ProjectDto.ProjectInfo("ProjectName",
            "Description", Map.of("crewInfo", "recruitmentInfo")
            , Map.of("recruitmentInfo", "recruitmentInfo")
            , timeInfo, timeInfo);

    ProjectDto.EnrollInfo enrollInfo = new ProjectDto.EnrollInfo("position", List.of("tools"), "4", 1);
    ProjectDto.ProjectEnrollRequest projectEnrollRequest = new ProjectDto.ProjectEnrollRequest(enrollInfo);
    ProjectDto.ProjectCreationRequest projectCreationRequest = new ProjectDto.ProjectCreationRequest(enrollInfo,projectInfo);


    @Test
    void getProjects() throws Exception {
        mockMvc.perform(get(basePath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$[0].projectInfo.description").value("Description"))
                .andExpect(jsonPath("$[0].projectInfo.crewInfo.crewInfo").value("recruitmentInfo"))
                .andExpect(jsonPath("$[0].projectInfo.recruitmentInfo.recruitmentInfo").value("recruitmentInfo"))
                .andExpect(jsonPath("$[0].projectInfo.expectedProjectDuration.startDate").exists())
                .andExpect(jsonPath("$[0].projectInfo.expectedProjectDuration.endDate").exists())
                .andExpect(jsonPath("$[0].projectInfo.expectedRecruitmentDuration.startDate").exists())
                .andExpect(jsonPath("$[0].projectInfo.expectedRecruitmentDuration.endDate").exists())
                .andExpect(jsonPath("$[1].projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$[1].projectInfo.description").value("Description"))
        ;
    }
    @Test
    void enrollProject() throws Exception {
        mockMvc.perform(post(basePath+"/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectEnrollRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enrollInfo.position").value("position"))
                .andExpect(jsonPath("$.enrollInfo.tools[0]").value("tools"))
                .andExpect(jsonPath("$.enrollInfo.years").value("4"))
                .andExpect(jsonPath("$.enrollInfo.involvements").value(1));
    }
    @Test
    void createProject() throws Exception {
        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectCreationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$.projectInfo.description").value("Description"))
                .andExpect(jsonPath("$.projectInfo.crewInfo.crewInfo").value("recruitmentInfo"));
    }
    @Test
    void updateProject() throws Exception {
        mockMvc.perform(patch(basePath+"/{projectId}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$.projectInfo.description").value("Description"))
                .andExpect(jsonPath("$.projectInfo.crewInfo.crewInfo").value("recruitmentInfo"));
    }
    @Test
    void deleteProject() throws Exception {
        mockMvc.perform(delete(basePath+"/{projectId}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("deleted"));
    }


}