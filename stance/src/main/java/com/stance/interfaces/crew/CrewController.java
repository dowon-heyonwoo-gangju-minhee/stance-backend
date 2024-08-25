package com.stance.interfaces.crew;

import com.stance.domain.crew.CrewInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crews")
public class CrewController {
    private final CrewInfoService crewService;

    public CrewController(CrewInfoService crewService) {
        this.crewService = crewService;
    }
    @Operation(summary = "회원 가입", description = "회원을 가입합니다.")
    @ApiResponse(responseCode = "200", description = "회원 가입 성공",
            content = @Content(schema = @Schema(implementation = CrewDto.CreateCrewResponse.class)))
    @PostMapping
    public CrewDto.CreateCrewResponse createCrew(@RequestBody CrewDto.CreateCrewRequest request) {
        request.validate();
        return CrewMapper.toResponse(
                crewService.createCrew(
                        CrewMapper.toCreate(request)
                )
        );
    }
}
