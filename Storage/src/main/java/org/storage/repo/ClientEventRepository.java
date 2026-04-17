package org.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.storage.entities.ClientEvent;

public interface ClientEventRepository extends JpaRepository<ClientEvent, Long> {

}
