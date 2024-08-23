package com.stance.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stance.domain.project.ProjectService;
import com.stance.domain.crew.CrewInfo;
import com.stance.domain.crew.RecruitmentInfo;
import com.stance.domain.period.ExpectedProjectDuration;
import com.stance.domain.period.ExpectedRecruitmentDuration;
import com.stance.domain.project.*;
import com.stance.domain.tools.Tools;
import com.stance.interfaces.project.ProjectController;
import com.stance.interfaces.project.ProjectDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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


    ExpectedRecruitmentDuration expectedRecruitmentDuration = new ExpectedRecruitmentDuration(LocalDateTime.now(),LocalDateTime.now().plusDays(7));
    ExpectedProjectDuration expectedProjectDuration = new ExpectedProjectDuration(LocalDateTime.now(),LocalDateTime.now().plusDays(7));
    List<Tools> tools = Collections.singletonList(new Tools("tools"));
    ProjectInfo projectInfo = new ProjectInfo("ProjectName",
            "Description",List.of(new CrewInfo("githubName", "githubEmail", "nickName", "position", List.of(new Tools("react")), 1L))
            , List.of(new RecruitmentInfo("position",List.of(new Tools("react")), 1L))
            , expectedProjectDuration, expectedRecruitmentDuration);

    private final String projectName = "ProjectName";
    ProjectDto.EnrollRequest enrollRequest = new ProjectDto.EnrollRequest(projectName,);
    ProjectDto.CreationRequest creationRequest = new ProjectDto.CreationRequest(enrollInfo,projectInfo);


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
                        .content(objectMapper.writeValueAsString(enrollRequest)))
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
                        .content(objectMapper.writeValueAsString(creationRequest)))
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