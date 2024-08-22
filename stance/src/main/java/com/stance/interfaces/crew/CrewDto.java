package com.stance.interfaces.crew;

import com.stance.domain.tools.Tools;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CrewDto {
    public record CreateCrewRequest(String githubName,
                                    String githubEmail,
                                    String nickName,
                                    String position,
                                    List<Tools> tools,
                                    Long years) {
        public void validate() {
            if (githubName.isEmpty()) {
                throw new IllegalArgumentException("깃허브 이름이 비어있습니다.");
            }
            if (githubEmail.isEmpty()) {
                throw new IllegalArgumentException("깃허브 이메일이 비어있습니다.");
            }
            if (nickName.isEmpty()) {
                throw new IllegalArgumentException("닉네임이 비어있습니다.");
            }
            if (position.isEmpty()) {
                throw new IllegalArgumentException("포지션이 비어있습니다.");
            }
            if (tools.isEmpty()) {
                throw new IllegalArgumentException("툴이 비어있습니다.");
            }
            if (years <= 0) {
                throw new IllegalArgumentException("경력은 0보다 커야 합니다.");
            }
        }
    }

    public record CreateCrewResponse(String nickName,
                                     String position,
                                     List<Tools> tools,
                                     Long years) {

    }
}
