package com.stance.infra.project;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProjectParticipationTracker {
    private final Set<Long> completedProjects = Collections.synchronizedSet(new HashSet<>());

    public boolean markAsCompleted(Long projectId) {
        return completedProjects.add(projectId);
    }

    public boolean isCompleted(Long projectId) {
        return completedProjects.contains(projectId);
    }
}
