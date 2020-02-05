package com.tavisca.testcontainersexample.service;

import com.tavisca.testcontainersexample.model.Person;
import com.tavisca.testcontainersexample.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public String savePerson(Person person) {
        personRepository.save(person);
        return personRepository.findByName(person.getName()).getId();
    }
}
