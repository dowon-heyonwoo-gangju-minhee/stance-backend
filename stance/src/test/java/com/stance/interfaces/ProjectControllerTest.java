package com.stance.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stance.application.ProjectFacade;
import com.stance.domain.project.ProjectService;
import com.stance.domain.crew.CrewInfo;
import com.stance.domain.recruitment.RecruitmentInfo;
import com.stance.domain.period.ExpectedProjectDuration;
import com.stance.domain.period.ExpectedRecruitmentDuration;
import com.stance.domain.project.*;
import com.stance.domain.tools.Tools;
import com.stance.interfaces.project.ProjectController;
import com.stance.interfaces.project.ProjectDto;
import com.stance.interfaces.project.ProjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
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
    private ProjectFacade projectFacade;

    @MockBean
    private ProjectService projectService;

    ExpectedRecruitmentDuration expectedRecruitmentDuration = new ExpectedRecruitmentDuration(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
    ExpectedProjectDuration expectedProjectDuration = new ExpectedProjectDuration(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
    List<Tools> tools = Collections.singletonList(new Tools("tools"));
    private final String crewEmail = "crewEmail";
    ProjectInfo projectInfo = new ProjectInfo("ProjectName",
            "Description", new CrewInfo("githubName", "githubEmail", "nickName", "position", tools, 1L)
            , List.of(new RecruitmentInfo("position", List.of(new Tools("react")), 1L))
            , expectedProjectDuration, expectedRecruitmentDuration);

    private final String projectName = "ProjectName";
    ProjectDto.EnrollRequest enrollRequest = new ProjectDto.EnrollRequest(projectName, crewEmail);
    ProjectDto.CreationRequest creationRequest = new ProjectDto.CreationRequest(projectInfo);


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
    }

    @Test
    void enrollProject() throws Exception {
        when(projectFacade.enrollProject(ProjectMapper.toEnroll(enrollRequest)))
                .thenReturn(projectInfo);
        mockMvc.perform(post(basePath + "/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enrollRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectInfo.crewInfo.position").value("position"))
                .andExpect(jsonPath("$.projectInfo.crewInfo.years").value(1));
    }

    @Test
    void createProject() throws Exception {
        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$.projectInfo.description").value("Description"));
    }

    @Test
    void updateProject() throws Exception {
        when(projectFacade.patchProject(any(ProjectCommand.Patch.class)))
                .thenReturn(projectInfo);
        mockMvc.perform(patch(basePath + "/{projectName}", "ProjectName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectInfo.projectName").value("ProjectName"))
                .andExpect(jsonPath("$.projectInfo.description").value("Description"));
    }

    @Test
    void deleteProject() throws Exception {
        when(projectFacade.deleteProject("ProjectName"))
                .thenReturn(true);
        mockMvc.perform(delete(basePath + "/{projectName}", "ProjectName"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Deleted"));
    }


}