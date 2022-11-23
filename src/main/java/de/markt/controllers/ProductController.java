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

import de.markt.controllers.errors.ItemNotFoundException;
import de.markt.entities.Product;
import de.markt.repositories.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository pr;

	@GetMapping(value = "/products")
	public MappingJacksonValue getProd() {
		List<Product> list = pr.findAll();
		if (list.size() == 0)
			throw new ItemNotFoundException("No products");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "description", "name",
				"category", "documents");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterProductProps", filter)
				.addFilter("FilterCategoryProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"))
				.addFilter("FilterDocumentProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "data"));
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/products")
	public ResponseEntity<Object> addProd(@RequestBody Product p) {
		try {
			Product savedProduct = pr.save(p);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedProduct.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ItemNotFoundException("Error");
	}
}