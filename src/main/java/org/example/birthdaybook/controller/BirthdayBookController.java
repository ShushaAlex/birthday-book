package org.example.birthdaybook.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.example.birthdaybook.data.entity.Person;
import org.example.birthdaybook.repository.PersonRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class BirthdayBookController {

    private final PersonRepository personRepository;

    @PostMapping()
    public Person addPerson(@RequestBody @Valid Person person) {
        return personRepository.addPerson(person);
    }

    @GetMapping()
    public Set<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/findByDate")
    public Set<Person> findByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") @PastOrPresent
            LocalDate date) {

        return personRepository.findByDate(date);
    }

    @GetMapping("/findByName")
    public  Set<Person> findByNameAndSurname(
            @RequestParam("firstName") @NotBlank String name,
            @RequestParam("secondName") @NotBlank String surname) {

        return personRepository.findByNameAndSurname(name, surname);
    }

}
