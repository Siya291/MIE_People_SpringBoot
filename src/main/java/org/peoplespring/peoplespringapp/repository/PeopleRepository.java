package org.peoplespring.peoplespringapp.repository;

import org.peoplespring.peoplespringapp.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {

    boolean existsByEmail(String email);

}
