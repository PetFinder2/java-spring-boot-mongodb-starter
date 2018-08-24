package com.mongodb.starter.controllers;

import com.mongodb.starter.models.Person;
import com.mongodb.starter.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("person")
    public ResponseEntity<Person> postPerson(@RequestBody Person person) {
        Person saved = personRepository.save(person);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("persons")
    public ResponseEntity<List<Person>> postPersons(@RequestBody List<Person> persons) {
        List<Person> people = personRepository.saveAll(persons);
        return ResponseEntity.ok(people);
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        Person person = personRepository.findOne(id);
        if (person == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(person);
    }

    @GetMapping("persons/{ids}")
    public ResponseEntity<List<Person>> getPersons(@PathVariable String ids) {
        List<String> listIds = Arrays.asList(ids.split(","));
        List<Person> persons = personRepository.findAll(listIds);
        return ResponseEntity.ok(persons);
    }

    @GetMapping("persons/count")
    public ResponseEntity<Long> getCount() {
        Long count = personRepository.count();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable String id) {
        long nbPersonDeleted = personRepository.delete(id);
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @DeleteMapping("persons/{ids}")
    public ResponseEntity<Long> deletePersons(@PathVariable String ids) {
        List<String> listIds = Arrays.asList(ids.split(","));
        long nbPersonDeleted = personRepository.delete(listIds);
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @DeleteMapping("persons")
    public ResponseEntity<Long> deletePersons() {
        long nbPersonDeleted = personRepository.deleteAll();
        return ResponseEntity.ok(nbPersonDeleted);
    }

    @PutMapping("person")
    public ResponseEntity<Person> putPerson(@RequestBody Person person) {
        Person updated = personRepository.update(person);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("persons")
    public ResponseEntity<Long> putPerson(@RequestBody List<Person> persons) {
        long updated = personRepository.update(persons);
        return ResponseEntity.ok(updated);
    }

}
