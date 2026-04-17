package org.storage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.storage.entities.AccountEvent;

public interface AccountEventRepository extends JpaRepository<AccountEvent, Long> {

}
