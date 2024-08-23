package com.stance.domain.membership;

import com.stance.infra.membership.MembershipEntity;

public interface MembershipRepository {
    void save(MembershipEntity membershipEntity);
}
