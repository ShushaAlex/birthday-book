package org.example.birthdaybook.service;

import jakarta.validation.Valid;
import org.example.birthdaybook.data.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Map;

@Validated
@Service
public interface BirthdayBookService {

    Person addPerson(@Valid Person person, LocalDate localDate);

    Map<Person, LocalDate> findAll();

    Map<Person, LocalDate> findByDate(LocalDate date);

    Map<Person, LocalDate> findByNameAndSurname(String name, String surname);
}
