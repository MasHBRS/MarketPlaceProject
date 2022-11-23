package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<de.markt.entities.Product, Long> {

}
