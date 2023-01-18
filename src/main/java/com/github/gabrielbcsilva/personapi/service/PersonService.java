package com.github.gabrielbcsilva.personapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.gabrielbcsilva.personapi.model.Person;
import com.github.gabrielbcsilva.personapi.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

	public List<Person> list() {
		return (List<Person>) personRepository.findAll();
	}
    public Person save(Person person){
        return personRepository.save(person);
    }
    public Optional<Person> findById(Long personId) {
        return personRepository.findById(personId);
    }
    public void delete(Long personId) {
        personRepository.deleteById(personId);
    }
    public boolean existsById(Long personId) {
        return personRepository.existsById(personId);
    }
  
 
}
