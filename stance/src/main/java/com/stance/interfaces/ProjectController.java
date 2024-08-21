package com.stance.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "프로젝트 관리 API")
public class ProjectController {

    ProjectDto.DurationInfo timeInfo = new ProjectDto.DurationInfo(LocalDateTime.now(),LocalDateTime.now().plusDays(7));
    ProjectDto.Tools tools = new ProjectDto.Tools("tools");
    ProjectDto.CrewInfo crewInfo = new ProjectDto.CrewInfo("githubName", "githubEmail", "nickName", "position", List.of(new ProjectDto.Tools("tools")), 1L);
    ProjectDto.RecruitmentInfo recruitmentInfo =new ProjectDto.RecruitmentInfo("position", List.of(new ProjectDto.Tools("tools")), 1L);
    ProjectDto.ProjectInfo projectInfo = new ProjectDto.ProjectInfo("ProjectName",
            "Description", List.of(crewInfo)
            , List.of(recruitmentInfo)
            , timeInfo, timeInfo);

    ProjectDto.EnrollInfo enrollInfo = new ProjectDto.EnrollInfo("gangju","position", List.of(tools), "4", 1);

    @Operation(summary = "모든 프로젝트 조회", description = "등록된 모든 프로젝트의 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 프로젝트 목록을 반환함",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectResponse.class)))
    @GetMapping
    public List<ProjectDto.ProjectResponse> getProjects() {
        List<ProjectDto.ProjectResponse> mock = new ArrayList<>();
        mock.add(new ProjectDto.ProjectResponse(projectInfo));
        mock.add(new ProjectDto.ProjectResponse(projectInfo));
        return mock;
    }

    @Operation(summary = "프로젝트 등록", description = "새로운 프로젝트 등록 정보를 제출합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 등록 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectEnrollResponse.class)))
    @PostMapping("/enroll")
    public ProjectDto.ProjectEnrollResponse enrollProject(@RequestBody ProjectDto.ProjectEnrollRequest request) {
        request.validate();
        return new ProjectDto.ProjectEnrollResponse(enrollInfo);
    }

    @Operation(summary = "프로젝트 생성", description = "새로운 프로젝트를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 생성 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectCreationResponse.class)))
    @PostMapping
    public ProjectDto.ProjectCreationResponse createProject(@RequestBody ProjectDto.ProjectCreationRequest request) {
        request.validate();
        return new ProjectDto.ProjectCreationResponse(projectInfo);
    }

    @Operation(summary = "프로젝트 수정", description = "기존 프로젝트 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 수정 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectResponse.class)))
    @PatchMapping("/{projectId}")
    public ProjectDto.ProjectResponse updateProject(
            @Parameter(description = "수정할 프로젝트의 ID") @PathVariable Long projectId) {
        return new ProjectDto.ProjectResponse(projectInfo);
    }

    @Operation(summary = "프로젝트 삭제", description = "지정된 프로젝트를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 삭제 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.DeleteResponse.class)))
    @DeleteMapping("/{projectId}")
    public ProjectDto.DeleteResponse deleteProject(
            @Parameter(description = "삭제할 프로젝트의 ID") @PathVariable Long projectId) {
        return new ProjectDto.DeleteResponse("Project s", "deleted");
    }
}