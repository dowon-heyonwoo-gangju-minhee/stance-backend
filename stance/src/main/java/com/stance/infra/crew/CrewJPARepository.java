package com.stance.infra.crew;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewJPARepository extends JpaRepository<CrewInfoEntity,Long> {
}
