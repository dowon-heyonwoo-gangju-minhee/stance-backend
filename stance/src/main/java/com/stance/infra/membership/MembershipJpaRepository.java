package com.stance.infra.membership;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipJpaRepository extends JpaRepository<MembershipEntity, Long> {
}
