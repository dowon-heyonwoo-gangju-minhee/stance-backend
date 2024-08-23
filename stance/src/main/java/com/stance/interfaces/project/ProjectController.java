package com.stance.interfaces.project;

import com.stance.application.ProjectFacade;
import com.stance.domain.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "프로젝트 관리 API")
public class ProjectController {
    private final ProjectFacade projectFacade;
    private final ProjectService projectService;

    public ProjectController(ProjectFacade projectFacade, ProjectService projectService) {
        this.projectFacade = projectFacade;
        this.projectService = projectService;
    }

    @Operation(summary = "모든 프로젝트 조회", description = "등록된 모든 프로젝트의 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 프로젝트 목록을 반환함",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectResponse.class)))
    @GetMapping
    public List<ProjectDto.ProjectResponse> getProjects() {
        return ProjectMapper.toGetResponse(
                projectService.getProjects());
    }

    @Operation(summary = "프로젝트 등록", description = "새로운 프로젝트 등록 정보를 제출합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 등록 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.EnrollResponse.class)))
    @PostMapping("/enroll")
    public ProjectDto.EnrollResponse enrollProject(@RequestBody ProjectDto.EnrollRequest request) {
        request.validate();
        return ProjectMapper.toPostResponse(
                projectFacade.enrollProject(
                        ProjectMapper.toEnroll(request)
                )
        );
    }

    @Operation(summary = "프로젝트 생성", description = "새로운 프로젝트를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 생성 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.CreationResponse.class)))
    @PostMapping
    public ProjectDto.CreationResponse createProject(@RequestBody ProjectDto.CreationRequest request) {
        request.validate();
        return new ProjectDto.CreationResponse(
                projectFacade.createProject(
                        ProjectMapper.toCreate(request.project())
                )
        );
    }

    @Operation(summary = "프로젝트 수정", description = "기존 프로젝트 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 수정 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.ProjectResponse.class)))
    @PatchMapping("/{projectName}")
    public ProjectDto.ProjectResponse updateProject(
            @Parameter(description = "수정할 프로젝트의 ID") @PathVariable String projectName,
            @Parameter(description = "수정할 프로젝트 정보") @RequestBody ProjectDto.ModifyRequest request)
     {
        return ProjectMapper.toPatchResponse(
                projectFacade.patchProject(
                        ProjectMapper.toPatch(projectName,request))
        );
    }

    @Operation(summary = "프로젝트 삭제", description = "지정된 프로젝트를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 삭제 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.DeleteResponse.class)))
    @DeleteMapping("/{projectName}")
    public ProjectDto.DeleteResponse deleteProject(
            @Parameter(description = "삭제할 프로젝트의 ID") @PathVariable String projectName) {
        return ProjectMapper.toDeleteResponse(
                projectFacade.deleteProject(projectName), projectName
        );
    }

    @Operation(summary = "프로젝트 모집 완료", description = "지정된 프로젝트의 모집을 완료합니다.")
    @ApiResponse(responseCode = "200", description = "프로젝트 모집 완료 성공",
            content = @Content(schema = @Schema(implementation = ProjectDto.CompleteResponse.class)))
    @PatchMapping("/{projectName}/toggle-recruitment")
    public ProjectDto.CompleteResponse toggleRecruitment(
            @Parameter(description = "모집 완료할 프로젝트의 ID") @PathVariable String projectName,
            @RequestBody ProjectDto.CompleteRequest request
            ) {
        request.validate();
        return ProjectMapper.toCompleteResponse(
                projectFacade.toggleRecruitment(
                        ProjectMapper.toState(request)), projectName
        );
    }
}