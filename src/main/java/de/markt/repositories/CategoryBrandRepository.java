package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 
 
public interface CategoryBrandRepository extends JpaRepository<de.markt.entities.CategoryBrand, Long> { 
}