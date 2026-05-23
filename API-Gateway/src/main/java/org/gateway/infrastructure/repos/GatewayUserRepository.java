package org.gateway.infrastructure.repos;

import org.gateway.infrastructure.entities.GatewayUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayUserRepository extends JpaRepository<GatewayUser, Long> {
    GatewayUser findByLogin(String login);
}
