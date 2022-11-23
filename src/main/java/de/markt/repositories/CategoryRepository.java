package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 

public interface CategoryRepository extends JpaRepository<de.markt.entities.Category, Long> { 
}