package org.peoplespring.peoplespringapp.controller;

import jakarta.validation.Valid;
import org.peoplespring.peoplespringapp.dto.request.PeopleRequest;
import org.peoplespring.peoplespringapp.dto.response.PeopleResponse;
import org.peoplespring.peoplespringapp.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/people")
public class PeopleController {

    private final PeopleService peopleService;
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    public ResponseEntity<PeopleResponse> create(@Valid @RequestBody PeopleRequest request) {
        PeopleResponse savedPeople = peopleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPeople);
    }

    @GetMapping
    public ResponseEntity<List<PeopleResponse>> findAll() {
        return ResponseEntity.ok(peopleService.findAll());
    }

    @GetMapping("/{peopleId}")
    public ResponseEntity<PeopleResponse> findById(@PathVariable Long peopleId) {

        PeopleResponse existingPerson = peopleService.findById(peopleId);

        return ResponseEntity.ok(existingPerson);
    }

    @PatchMapping("/{peopleId}")
    public ResponseEntity<PeopleResponse> update(@PathVariable Long peopleId, @Valid @RequestBody PeopleRequest request) {

        PeopleResponse existingPerson = peopleService.update(peopleId, request);

        return ResponseEntity.ok(existingPerson);
    }

    @DeleteMapping("/{peopleId}")
    public String delete(@PathVariable Long peopleId) {

        peopleService.delete(peopleId);

        return "Developer deleted successfully with id: " + peopleId;
    }
}
