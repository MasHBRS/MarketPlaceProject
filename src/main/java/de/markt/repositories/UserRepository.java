package de.markt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.markt.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}