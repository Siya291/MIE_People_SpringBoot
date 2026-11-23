package org.peoplespring.peoplespringapp.repository;

import org.peoplespring.peoplespringapp.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIDNot(String email, Long peopleId);

    Optional<PeopleEntity> findByEmail(String email);
}
