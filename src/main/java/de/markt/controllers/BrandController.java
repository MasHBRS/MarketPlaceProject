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

import de.markt.entities.Brand;
import de.markt.repositories.BrandRepository;

@RestController
public class BrandController {
	@Autowired
	BrandRepository br;

	@GetMapping(value = "/brands")
	public MappingJacksonValue getBrands() {
		List<Brand> list = br.findAll();

		// invoking static method filterOutAllExcept()
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterOptionProps", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/brands")
	public ResponseEntity<Object> addProd(@RequestBody Brand b) {
		try {
			Brand savedBrand = br.save(b);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedBrand.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
		}
		return null;
	}
}