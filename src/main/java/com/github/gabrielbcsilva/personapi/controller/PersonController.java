package com.github.gabrielbcsilva.personapi.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielbcsilva.personapi.exception.EntityUseException;
import com.github.gabrielbcsilva.personapi.exception.ResourceNotFoundException;
import com.github.gabrielbcsilva.personapi.model.Adress;
import com.github.gabrielbcsilva.personapi.model.Person;
import com.github.gabrielbcsilva.personapi.service.AdressService;
import com.github.gabrielbcsilva.personapi.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
	@Autowired
	PersonService personService;
	@Autowired
	AdressService adressService;

	@GetMapping
	public List<Person> list() {
		return personService.list();
	}

	@GetMapping("/{personId}")
	public ResponseEntity<Person> search(@PathVariable Long personId) {
		Optional<Person> person = personService.findById(personId);

		if (person.isPresent()) {
			return ResponseEntity.ok(person.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person save(@RequestBody Person person) {
		return personService.save(person);
	}

	@PutMapping("/{personId}")
	public ResponseEntity<Person> update(@PathVariable Long personId,
			@RequestBody Person person) {
		Optional<Person> foundPerson = personService.findById(personId);

		if (foundPerson.isPresent()) {

			foundPerson.get().setBirthDate(person.getBirthDate());
			foundPerson.get().setName(person.getName());
			if (person.getPrincipalAdress() != null) {
				Optional<Adress> foundAdress = adressService.findById(person.getPrincipalAdress().getId());
				if (!foundAdress.isPresent()) {
					throw new ResourceNotFoundException(
							"Not found Adress with id = " + person.getPrincipalAdress().getId());
				}
				if (personId.compareTo(foundAdress.get().getPerson().getId()) != 0) {
					throw new ResourceNotFoundException("The Adress with id = " + person.getPrincipalAdress().getId()
							+ " it's not able for this user.");
				}
				foundPerson.get().setPrincipalAdress(foundAdress.get());
			}
			final Person updatedPerson = personService.save(foundPerson.get());

			return ResponseEntity.ok(updatedPerson);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long personId) {
		try {
			personService.delete(personId);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (EntityUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
}
