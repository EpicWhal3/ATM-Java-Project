package org.gateway.infrastructure.repos;

import org.gateway.infrastructure.entities.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlackListRepository extends JpaRepository<TokenBlacklist, Long> {
    TokenBlacklist findByToken(String token);
    boolean existsByToken(String token);
}
