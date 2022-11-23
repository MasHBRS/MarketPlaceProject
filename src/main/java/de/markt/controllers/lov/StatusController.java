package de.markt.controllers.lov;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.entities.lov.Status;

@RestController
public class StatusController {
	@Autowired
	de.markt.repositories.lov.StatusRepository sr;

	@GetMapping(value = "/staus") // bayad az user beresim be basket
	public MappingJacksonValue getStatus() {
		List<Status> list = sr.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "en_name", "fa_name",
				"comment");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterStatusProps", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/staus")
	public ResponseEntity<Object> addBasket(@RequestBody Status s, Authentication authentication) {
		try {
			Status savedStatus = sr.save(s);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedStatus.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
		}
		return null;
	}

	@DeleteMapping(value = "/staus/{stausId}")
	public Boolean removeBasket(@PathVariable long stausId, Authentication authentication) {
		sr.deleteById(stausId);
		return true;// It should be changed !
	}

	@GetMapping(value = "/staus/{stausId}")
	public MappingJacksonValue getOneStatus(@PathVariable long stausId) {

		Optional<Status> basket = sr.findById(stausId);
		if (!basket.isPresent())
			return null;
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "en_name", "fa_name",
				"comment");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterStatusProps", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(basket.get());
		mapping.setFilters(filters);
		return mapping;
	}
}
