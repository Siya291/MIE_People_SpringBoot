package org.peoplespring.peoplespringapp.service;

import org.peoplespring.peoplespringapp.dto.request.PeopleRequest;
import org.peoplespring.peoplespringapp.dto.response.PeopleResponse;
import org.peoplespring.peoplespringapp.entity.PeopleEntity;

import java.util.List;

public interface PeopleService {

    PeopleResponse create(PeopleRequest request);

    List<PeopleEntity> findAll();

    PeopleResponse findById(Long peopleId);

    PeopleResponse update(Long peopleId, PeopleRequest request);

    void delete(Long peopleId);
}
