package org.example.birthdaybook.repository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.birthdaybook.data.entity.Person;
import org.example.birthdaybook.service.BirthdayBookService;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PersonRepository implements BirthdayBookService {

    private final Map<Person, LocalDate> personLocalDateMap = new HashMap<>();

    @Override
    public Person addPerson(Person person, LocalDate localDate) {
        personLocalDateMap.put(person, localDate);
        return person;
    }

    @Override
    public  Map<Person, LocalDate> findAll() {
        return Map.copyOf(personLocalDateMap);
    }

    @Override
    public  Map<Person, LocalDate> findByDate(LocalDate date) {
        return personLocalDateMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(date))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public  Map<Person, LocalDate> findByNameAndSurname(String name, String surname) {
        return personLocalDateMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name) && entry.getKey().getSurname().equals(surname))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
