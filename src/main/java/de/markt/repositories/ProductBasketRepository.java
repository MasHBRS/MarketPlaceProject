package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.markt.entities.ProductBasket;

public interface ProductBasketRepository extends JpaRepository<ProductBasket, Long> {
}