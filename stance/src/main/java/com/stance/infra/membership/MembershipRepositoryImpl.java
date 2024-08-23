package com.stance.infra.membership;

import com.stance.domain.membership.MembershipRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {
    private final MembershipJpaRepository projectMembershipJpaRepository;

    public MembershipRepositoryImpl(MembershipJpaRepository projectMembershipJpaRepository) {
        this.projectMembershipJpaRepository = projectMembershipJpaRepository;
    }

    @Override
    public void save(MembershipEntity membershipEntity) {
        projectMembershipJpaRepository.save(membershipEntity);
    }
}
