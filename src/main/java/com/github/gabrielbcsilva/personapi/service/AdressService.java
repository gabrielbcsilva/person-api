package com.github.gabrielbcsilva.personapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gabrielbcsilva.personapi.model.Adress;
import com.github.gabrielbcsilva.personapi.repository.AdressRepository;

@Service
public class AdressService {
    @Autowired
    AdressRepository adressRepository;

	
    public Adress save(Adress adress){
        return adressRepository.save(adress);
    }
    public Optional<Adress> findById(Long adressId) {
        return adressRepository.findById(adressId);
    }
    public void delete(Long adressId) {
        adressRepository.deleteById(adressId);
    }
    public void deleteByPerson(Long personId) {
        adressRepository.deleteByPerson(personId);
    }
    public List<Adress> findByPersonId(Long personId) {
        return adressRepository.findByPersonId(personId);
    }
    public List<Adress> list() {
        return (List<Adress>) adressRepository.findAll();
    }
  
 
}
