package com.stance.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stance.domain.project.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
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

    @MockBean
    private ProjectService projectService;

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
        when(projectService.getProjects()).thenReturn(List.of(projectInfo, projectInfo));

        String contentAsString = mockMvc.perform(get(basePath))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<ProjectDto.ProjectResponse> projectResponses = objectMapper.readValue(contentAsString,
                new TypeReference<>() {
                });

        System.out.println(projectResponses);

        assertFalse(projectResponses.isEmpty());
        assertEquals(2, projectResponses.size());

        ProjectDto.ProjectResponse firstProject = projectResponses.getFirst();
        assertEquals("ProjectName", firstProject.projectInfo().projectName());
        assertEquals("Description", firstProject.projectInfo().description());
        // 추가적인 필드 검증...
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