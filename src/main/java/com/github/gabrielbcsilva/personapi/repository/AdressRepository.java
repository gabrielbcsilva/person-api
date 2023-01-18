package com.github.gabrielbcsilva.personapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.github.gabrielbcsilva.personapi.model.Adress;

public interface AdressRepository extends CrudRepository<Adress, Long> {
    List<Adress> findByPersonId(Long personId);

    @Transactional
    void deleteByPerson(long personId);
}
