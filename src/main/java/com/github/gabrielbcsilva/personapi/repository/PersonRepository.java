package com.github.gabrielbcsilva.personapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.gabrielbcsilva.personapi.model.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{
    
}
