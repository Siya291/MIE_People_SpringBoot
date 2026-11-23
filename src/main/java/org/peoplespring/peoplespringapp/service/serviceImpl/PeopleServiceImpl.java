package org.peoplespring.peoplespringapp.service.serviceImpl;

import org.peoplespring.peoplespringapp.dto.request.PeopleRequest;
import org.peoplespring.peoplespringapp.dto.response.PeopleResponse;
import org.peoplespring.peoplespringapp.entity.PeopleEntity;
import org.peoplespring.peoplespringapp.exceptions.ResourceNotFoundException;
import org.peoplespring.peoplespringapp.repository.PeopleRepository;
import org.peoplespring.peoplespringapp.service.PeopleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;
    public PeopleServiceImpl(PeopleRepository  peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    @Transactional
    public PeopleResponse create(PeopleRequest request) {
        PeopleEntity people = new PeopleEntity();
        people.setName(request.name());
        people.setEmail(request.email());

        PeopleEntity savedPeople = peopleRepository.save(people);

        return mapToResponse(savedPeople);
    }

    @Override
    @Transactional
    public List<PeopleResponse> findAll() {
        return peopleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public PeopleResponse findById(Long peopleId) {

        PeopleEntity people = getPersonEntityById(peopleId);

        return mapToResponse(people);
    }

    @Override
    @Transactional
    public PeopleResponse update(Long peopleId, PeopleRequest request) {

        PeopleEntity existingPerson = getPersonEntityById(peopleId);

        existingPerson.setName(request.name());
        existingPerson.setEmail(request.email());

        PeopleEntity updatedPerson = peopleRepository.save(existingPerson);

        return mapToResponse(updatedPerson);
    }

    @Override
    public void delete(Long peopleId) {

        PeopleEntity existingPerson = getPersonEntityById(peopleId);

        peopleRepository.delete(existingPerson);
    }

    private PeopleEntity getPersonEntityById(Long peopleId) {
        return peopleRepository.findById(peopleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person not found: " + peopleId));
    }

    private PeopleResponse mapToResponse(PeopleEntity people) {

        return new PeopleResponse(
                people.getPeopleId(),
                people.getName(),
                people.getEmail()
        );
    }
}
