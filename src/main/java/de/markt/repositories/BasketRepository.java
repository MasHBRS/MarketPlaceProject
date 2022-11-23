package de.markt.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import de.markt.entities.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	public List<Basket> findByUserID(long userID);

	public List<Basket> findByUserIDAndStatusId(long userID, long statusID);

	public Basket findByIdAndUserID(long id, long userID);

	@Transactional
	@Modifying
	public void deleteByIdAndUserID(long basketId, long userID);
}
