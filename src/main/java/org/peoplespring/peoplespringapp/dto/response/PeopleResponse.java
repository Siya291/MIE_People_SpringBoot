package org.peoplespring.peoplespringapp.dto.response;

public record PeopleResponse(
        Long peopleId,
        String name,
        String email
) {
}
