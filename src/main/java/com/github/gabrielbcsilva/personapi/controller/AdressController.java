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
import com.github.gabrielbcsilva.personapi.service.AdressService;
import com.github.gabrielbcsilva.personapi.service.PersonService;

@RestController
@RequestMapping
public class AdressController {
    @Autowired
    PersonService personService;
    
	@Autowired
    AdressService adressService;

    @GetMapping(value = "/person/{personId}/adress")
	public List<Adress> listByPerson(@PathVariable(value="personId") Long personId) {
		if (!personService.existsById(personId)) {
			throw new ResourceNotFoundException("Not found Person with id = " + personId);
		  }
		  List<Adress> adresses = adressService.findByPersonId(personId);
		  return adresses;
	}
    @GetMapping(value = "/adress")
	public List<Adress> list() {
		  List<Adress> adresses = adressService.list();
		  return adresses;
	}
      
    @GetMapping("/adress/{adressId}")
	public ResponseEntity<Adress> search(@PathVariable Long adressId) {
		Optional<Adress> adress = adressService.findById(adressId);
		
		if (adress.isPresent()) {
			return ResponseEntity.ok(adress.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/person/{personId}/adress")
	@ResponseStatus(HttpStatus.CREATED)
	public Adress save(@PathVariable(value="personId") Long personId,@RequestBody Adress adress) {
		if (!personService.existsById(personId)) {
			throw new ResourceNotFoundException("Not found Person with id = " + personId);
		  }
		  adress.setPerson(personService.findById(personId).get());
		return adressService.save(adress);
	}
	
	@PutMapping("/adress/{adressId}")
	public ResponseEntity<Adress> update(@PathVariable Long adressId,
			@RequestBody Adress adress) {
		Optional<Adress> foundAdress = adressService.findById(adressId);
		
		if (foundAdress.isPresent()) {
			
			foundAdress.get().setStreet(adress.getStreet());
			foundAdress.get().setCep(adress.getCep());
			foundAdress.get().setNumber(adress.getNumber());
			foundAdress.get().setCity(adress.getCity());
			
			final Adress updatedAdress = adressService.save(foundAdress.get());

			return ResponseEntity.ok(updatedAdress);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{adressId}")
	public ResponseEntity<?> delete(@PathVariable Long adressId) {
		try {
			adressService.delete(adressId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
}
