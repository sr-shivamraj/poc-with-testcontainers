package com.tavisca.testcontainersexample.controller;

import com.tavisca.testcontainersexample.model.Person;
import com.tavisca.testcontainersexample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {

        System.out.println(person);
        String generatedId=personService.savePerson(person);
        String response="ok";
        if(generatedId.isEmpty() || generatedId==null)
            response="notok";
        return ResponseEntity.ok(response);
    }

    @GetMapping("/person")
    public ResponseEntity<String> getPerson() {
        return ResponseEntity.ok("");
    }

}
