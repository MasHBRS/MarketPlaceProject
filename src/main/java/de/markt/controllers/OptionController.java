package de.markt.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.entities.Option;
import de.markt.repositories.OptionRepository;

@RestController
public class OptionController {
	@Autowired
	OptionRepository or;

	@GetMapping(value = "/options")
	public MappingJacksonValue getOptions() {
		List<Option> list = or.findAll();

		// invoking static method filterOutAllExcept()
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "description", "name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterOptionProps", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/options")
	public ResponseEntity<Object> addProd(@RequestBody Option op) {
		try {
			Option savedOption = or.save(op);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedOption.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
		}
		return null;
	}
}
