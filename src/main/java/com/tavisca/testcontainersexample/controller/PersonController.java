package com.tavisca.testcontainersexample.controller;

import com.tavisca.testcontainersexample.model.Person;
import com.tavisca.testcontainersexample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<String> addPerson(Person person) {
        String generatedId=personService.savePerson(person);
        return ResponseEntity.ok(generatedId);
    }

    @GetMapping("/person")
    public ResponseEntity<String> getPerson() {
        return ResponseEntity.ok("");
    }
}
