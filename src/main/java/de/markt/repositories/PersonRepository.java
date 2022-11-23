package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.markt.entities.Person; 

public interface PersonRepository extends JpaRepository<Person, Long> { 
}