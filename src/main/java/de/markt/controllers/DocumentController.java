package de.markt.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.entities.Document;
import de.markt.entities.Product;
import de.markt.repositories.DocumentRepository;
import de.markt.repositories.ProductRepository;

@RestController
public class DocumentController {
	@Autowired
	DocumentRepository dr;
	@Autowired
	ProductRepository pr;

	@GetMapping(value = "/docuements") // bayad az user beresim be basket
	public MappingJacksonValue getBaskets(Authentication authentication) {
		// UserDetailsImpl userDetails = (UserDetailsImpl)
		// authentication.getPrincipal();
		List<Document> list = dr.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "data", "name", "product");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterDocumentProps", filter)
				.addFilter("FilterProductProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "data", "name"));
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

	@PostMapping(value = "/documents")
	public ResponseEntity<Object> addBasket(@RequestParam("file") MultipartFile file, @RequestParam String name,
			@RequestParam long productId, Authentication authentication) {
		try {

			// UserDetailsImpl userDetails = (UserDetailsImpl)
			Optional<Product> product = pr.findById(productId);
			if (product.isPresent()) {
				Document document = new Document(file.getBytes(), name, product.get());
				document = dr.save(document);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(document.getId()).toUri();
				return ResponseEntity.created(location).build();
			}
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
	 */
}