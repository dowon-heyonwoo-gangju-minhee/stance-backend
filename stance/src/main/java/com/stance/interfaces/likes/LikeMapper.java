package com.stance.interfaces.likes;

import com.stance.domain.like.LikeCommand;

public class LikeMapper {
    public static LikeCommand.Like toProjectLike(LikeDto.ProjectLikeRequest request) {
        return new LikeCommand.Like(request.projectName(), request.crewEmail());
    }
    public static LikeDto.LikeResponse toLikeResponse(Boolean result) {
        return new LikeDto.LikeResponse(result);
    }
}
