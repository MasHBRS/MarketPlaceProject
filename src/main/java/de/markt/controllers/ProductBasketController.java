package de.markt.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import de.markt.security.UserDetailsImpl;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.controllers.errors.ItemNotFoundException;
import de.markt.entities.Basket;
import de.markt.entities.Product;
import de.markt.entities.ProductBasket;
import de.markt.repositories.BasketRepository;
import de.markt.repositories.ProductBasketRepository;
import de.markt.repositories.ProductRepository;

@RestController
public class ProductBasketController {
	@Autowired
	BasketRepository br;
	@Autowired
	ProductRepository pr;
	@Autowired
	ProductBasketRepository pbr;

	@PostMapping(value = "/baskets/{basketId}/products/{productId}/orderItem")
	public ResponseEntity<Object> addOrder(@PathVariable long basketId, @PathVariable long productId,
			@RequestBody ProductBasket pb, Authentication authentication) {
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			Optional<Basket> basketOp = br.findById(basketId);
			Optional<Product> productOp = pr.findById(productId);
			if (basketOp.isPresent() && productOp.isPresent() && basketOp.get().getUserID() == userDetails.getId()) {
				pb.setBasket(basketOp.get());
				pb.setProduct(productOp.get());
				pb = pbr.save(pb);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pb.getId())
						.toUri();
				return ResponseEntity.created(location).build();
			}
		} catch (Exception e) {
		}
		throw new ItemNotFoundException("No Such Basket Or Product");
	}

	@GetMapping(value = "/baskets/{basketId}/orderItems")
	public MappingJacksonValue getBasket(@PathVariable long basketId, Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Basket basket = br.findByIdAndUserID(basketId, userDetails.getId());
		if (basket == null)
			throw new ItemNotFoundException("No Such Basket");
		List<ProductBasket> pbs = basket.getProductBaskets();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "count", "description",
				"basket", "product");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterProductBasketProps", filter)
				.addFilter("FilterBasketProps", SimpleBeanPropertyFilter.filterOutAllExcept("id"))
				.addFilter("FilterProcutProps", SimpleBeanPropertyFilter.filterOutAllExcept("id"));
		MappingJacksonValue mapping = new MappingJacksonValue(pbs);
		mapping.setFilters(filters);
		return mapping;
	}

	@DeleteMapping(value = "/baskets/{basketId}/orderItem/{orderItemId}")
	public String removeOrder(@PathVariable long basketId, @PathVariable long orderItemId,
			Authentication authentication) {
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Basket basket = br.findByIdAndUserID(basketId, userDetails.getId());// Momkene Inja basket null bashe handle esh
																			// konam
		List<ProductBasket> pbs = basket.getProductBaskets().stream().filter(a -> a.getId() == orderItemId)
				.collect(Collectors.toList());
		if (pbs.size() > 0)
			pbr.deleteById(orderItemId);
		return "Done ^^";
	}
}