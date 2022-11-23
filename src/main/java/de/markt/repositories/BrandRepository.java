package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.markt.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}