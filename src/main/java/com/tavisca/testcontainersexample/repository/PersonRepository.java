package com.tavisca.testcontainersexample.repository;

import com.tavisca.testcontainersexample.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    Person findByName(String name);
}
