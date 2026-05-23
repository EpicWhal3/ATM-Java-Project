package org.bank.memory.repos;

import org.bank.memory.entities.users.HairColor;
import org.bank.memory.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByHairColorAndGender(HairColor hairColor, String gender);

    List<User> findByHairColor(HairColor hairColor);

    List<User> findByGender(String gender);

    boolean existsByLogin(String login);

    User findByLogin(String login);
}
