//package com.stance.interfaces.likes;
//
//import com.stance.application.LikesFacade;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/likes")
//@Tag(name = "Like", description = "좋아요 관리 API")
//public class LikeController {
//
//    private final LikesFacade likesFacade;
//
//    public LikeController(LikesFacade likesFacade) {
//        this.likesFacade = likesFacade;
//    }
//
//    @Operation(summary = "프로젝트 좋아요", description = "특정 프로젝트에 좋아요를 추가합니다.")
//    @ApiResponse(responseCode = "200", description = "좋아요 추가 성공",
//            content = @Content(schema = @Schema(implementation = LikeDto.LikeResponse.class)))
//    @PostMapping("/project/{projectId}")
//    public LikeDto.LikeResponse likeProject(
//            @Parameter(description = "좋아요 받는 프로젝트 이름, 크루 이메일 ") @RequestBody LikeDto.ProjectLikeRequest request){
//        request.validate();
//        return LikeMapper.toLikeResponse(
//                likesFacade.likeProject(LikeMapper.toProjectLike(request))
//        );
//    }
//
//    @Operation(summary = "크루 좋아요", description = "특정 크루에 좋아요를 추가합니다.")
//    @ApiResponse(responseCode = "200", description = "좋아요 추가 성공",
//            content = @Content(schema = @Schema(implementation = LikeDto.LikeResponse.class)))
//    @PostMapping("/crew/like")
//    public LikeDto.LikeResponse likeCrew(
//            @Parameter(description = "좋아요 누르는 크루 이메일, 좋아요 받는 크루 이메일") @RequestBody LikeDto.CrewLikeRequest request){
//        request.validate();
//        return LikeMapper.toLikeResponse(
//                likesFacade.likeCrew(likerId, likedCrewId)
//        );
//    }
//
//    @Operation(summary = "프로젝트 좋아요 누른 크루 조회", description = "특정 프로젝트에 좋아요를 누른 크루 목록을 반환합니다.")
//    @ApiResponse(responseCode = "200", description = "크루 목록 조회 성공",
//            content = @Content(schema = @Schema(implementation = LikeDto.CrewInfoResponse.class)))
//    @GetMapping("/project/{projectId}/likers")
//    public List<LikeDto.CrewInfoResponse> getProjectLikers(
//            @Parameter(description = "조회할 프로젝트 ID") @PathVariable Long projectId) {
//        return LikeMapper.toCrewInfoResponse(
//                likesFacade.getProjectLikers(projectId)
//        );
//    }
//
//    @Operation(summary = "크루가 좋아요 누른 프로젝트 조회", description = "특정 크루가 좋아요를 누른 프로젝트 목록을 반환합니다.")
//    @ApiResponse(responseCode = "200", description = "프로젝트 목록 조회 성공",
//            content = @Content(schema = @Schema(implementation = LikeDto.ProjectResponse.class)))
//    @GetMapping("/crew/{crewId}/liked-projects")
//    public List<LikeDto.ProjectResponse> getLikedProjects(
//            @Parameter(description = "조회할 크루 ID") @PathVariable Long crewId) {
//        return LikeMapper.toProjectResponse(
//                likesFacade.getLikedProjects(crewId)
//        );
//    }
//
//    @Operation(summary = "크루가 좋아요 누른 크루 조회", description = "특정 크루가 좋아요를 누른 다른 크루 목록을 반환합니다.")
//    @ApiResponse(responseCode = "200", description = "크루 목록 조회 성공",
//            content = @Content(schema = @Schema(implementation = LikeDto.CrewInfoResponse.class)))
//    @GetMapping("/crew/{crewId}/liked-crews")
//    public List<LikeDto.CrewInfoResponse> getLikedCrews(
//            @Parameter(description = "조회할 크루 ID") @PathVariable Long crewId) {
//        return LikeMapper.toCrewInfoResponse(
//                likesFacade.getLikedCrews(crewId)
//        );
//    }
//}