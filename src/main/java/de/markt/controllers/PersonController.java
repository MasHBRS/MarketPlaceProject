package de.markt.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.entities.Person;
import de.markt.entities.User;
import de.markt.repositories.PersonRepository;
import de.markt.repositories.UserRepository;
import de.markt.security.UserDetailsImpl;

@RestController
public class PersonController {
	@Autowired
	UserRepository ur;

	@Autowired
	PersonRepository pr;

	@GetMapping(value = "/Persons") // bayad az user beresim be basket
	public MappingJacksonValue getBaskets(Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		User user = ur.findByUsername(userDetails.getUsername());
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "username");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterUserProps", filter).addFilter(
				"FilterPersonProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "family", "users"));
		MappingJacksonValue mapping = new MappingJacksonValue(user.getPerson());
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/Persons")
	public ResponseEntity<Object> addPerson(@RequestBody Person person, Authentication authentication) {
		try {
			Person p = pr.save(person);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
		}
		return null;
	}

	/*
	 * @DeleteMapping(value = "/baskets/{basketId}") public Boolean
	 * removeBasket(@PathVariable long basketId, Authentication authentication) {
	 * UserDetailsImpl userDetails = (UserDetailsImpl)
	 * authentication.getPrincipal(); br.deleteByIdAndUserID(basketId,
	 * userDetails.getId()); System.out.println(">>>>>>>>>>>"+basketId); return
	 * false;// It should be changed ! }
	 * 
	 * @GetMapping(value = "/baskets/{basketId}") public MappingJacksonValue
	 * getBasket(@PathVariable long basketId,Authentication authentication) {
	 * System.out.println("+++"); UserDetailsImpl userDetails = (UserDetailsImpl)
	 * authentication.getPrincipal(); Basket basket=br.findByIdAndUserID(basketId,
	 * userDetails.getId()); //List<ProductBasket>pb= basket.getProductBaskets();
	 * SimpleBeanPropertyFilter filter =
	 * SimpleBeanPropertyFilter.filterOutAllExcept("id", "code", "name");
	 * FilterProvider filters = new
	 * SimpleFilterProvider().addFilter("FilterBasketProps", filter);
	 * MappingJacksonValue mapping = new MappingJacksonValue(basket);
	 * mapping.setFilters(filters); return mapping; }
	 */

}