package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.markt.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
