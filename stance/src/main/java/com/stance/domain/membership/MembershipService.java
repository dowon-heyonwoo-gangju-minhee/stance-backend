package com.stance.domain.membership;

import com.stance.infra.membership.MembershipEntity;
import org.springframework.stereotype.Component;

@Component
public class MembershipService {
    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void save(MembershipEntity membershipEntity) {
        membershipRepository.save(membershipEntity);
    }
}
