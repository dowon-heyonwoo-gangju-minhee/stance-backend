package com.stance.interfaces.likes;

public class LikeDto {
    public record ProjectLikeRequest(String projectName, String crewEmail) {
        public void validate() {
            if (projectName == null || projectName.isBlank()) {
                throw new IllegalArgumentException("projectName is null or empty");
            }
            if (crewEmail == null || crewEmail.isBlank()) {
                throw new IllegalArgumentException("crewEmail is null or empty");
            }
        }
    }

    public record LikeResponse(Boolean result) {
    }

    public record CrewLikeRequest(String likerEmail, String likedCrewEmail) {
        public void validate() {
            if (likerEmail == null || likerEmail.isBlank()) {
                throw new IllegalArgumentException("likerEmail is null or empty");
            }
            if (likedCrewEmail == null || likedCrewEmail.isBlank()) {
                throw new IllegalArgumentException("likedCrewEmail is null or empty");
            }
        }
    }
}
