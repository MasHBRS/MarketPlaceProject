package de.markt.repositories.lov;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.markt.entities.lov.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
	public Status findByEnName(String enName);
}