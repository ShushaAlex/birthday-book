package org.example.birthdaybook.repository;

import lombok.RequiredArgsConstructor;
import org.example.birthdaybook.data.entity.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PersonRepository {

    private final Set<Person> personSet = new HashSet<>();

    public Person addPerson(Person person) {
        personSet.add(person);
        return person;
    }

    public  Set<Person> findAll() {
        return Set.copyOf(personSet);
    }

    public  Set<Person> findByDate(LocalDate date) {
        return personSet
                .stream()
                .filter(p -> p.getDateOfBirth().equals(date))
                .collect(Collectors.toSet());
    }

    public  Set<Person> findByNameAndSurname(String name, String surname) {
        return personSet
                .stream()
                .filter(p -> p.getName().equals(name) && p.getSurname().equals(surname))
                .collect(Collectors.toSet());
    }
}
