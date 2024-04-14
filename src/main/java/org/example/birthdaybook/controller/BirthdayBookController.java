package org.example.birthdaybook.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.example.birthdaybook.data.entity.Person;
import org.example.birthdaybook.data.dto.PersonDto;
import org.example.birthdaybook.repository.PersonRepository;
import org.example.birthdaybook.util.DateParser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class BirthdayBookController {

    private final PersonRepository personRepository;

    @PostMapping()
    public Person addPerson(@RequestBody @Valid PersonDto personDto) throws ValidationException {
        LocalDate dateOfBirth = DateParser.stringToDate(personDto.getDateOfBirth());
        Person person = Person.builder()
                .name(personDto.getName())
                .surname(personDto.getSurname())
                .dateOfBirth(dateOfBirth).build();
        return personRepository.addPerson(person, dateOfBirth);
    }

    @GetMapping()
    public Map<Person, LocalDate> getAllPersons() {
        return personRepository.findAll();
    }
}
