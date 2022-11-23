package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.markt.entities.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
}