package de.markt.controllers;
import java.net.URI; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.converter.json.MappingJacksonValue; 
import org.springframework.security.core.Authentication; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 
 
import com.fasterxml.jackson.databind.ser.FilterProvider; 
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter; 
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.markt.controllers.errors.ItemNotFoundException;
import de.markt.entities.Basket;
import de.markt.entities.lov.Status;
import de.markt.repositories.*;
import de.markt.repositories.lov.StatusRepository;
import de.markt.security.UserDetailsImpl;
 
@RestController 
public class BasketController { 
        @Autowired 
        BasketRepository br; 
        @Autowired 
        StatusRepository sr; 
 
        Map<String, Long> savedValues = new HashMap<>(); 
 
        @GetMapping(value = "/baskets") // bayad az user beresim be basket 
        public MappingJacksonValue getBaskets(Authentication authentication) { 
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
                List<Basket> list = br.findByUserID(userDetails.getId()); 
                if(list.size()==0) 
                        throw new ItemNotFoundException("No Such Baskets"); 
                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "code", "name"); 
                FilterProvider filters = new SimpleFilterProvider().addFilter("FilterBasketProps", filter); 
                MappingJacksonValue mapping = new MappingJacksonValue(list); 
                mapping.setFilters(filters); 
                return mapping; 
        } 
 
        @PostMapping(value = "/baskets") 
        public ResponseEntity<Object> addBasket(Authentication authentication) { 
                try { 
                        Status openBasket = sr.findByEnName("Open_Basket"); 
 
                        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
                        if (!savedValues.containsKey("Open_Basket")) 
                                savedValues.put("Open_Basket", openBasket.getId()); 
 
                        List<Basket> baskets = br.findByUserIDAndStatusId(userDetails.getId(), savedValues.get("Open_Basket")); 
                        if (baskets.size() == 0) { 
                                Basket bk = new Basket(); 
                                bk.setStatus(openBasket); 
                                bk.setUserID(userDetails.getId()); 
                                bk = br.save(bk); 
                                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bk.getId()) 
                                                .toUri(); 
                                return ResponseEntity.created(location).build(); 
                        } else 
                                throw new ItemNotFoundException("No Such Basket"); 
                } catch (Exception e) { 
                        e.printStackTrace(); 
                } 
                throw new ItemNotFoundException("No Such Basket"); 
        } 
 
        @DeleteMapping(value = "/baskets/{basketId}") 
        public Boolean removeBasket(@PathVariable long basketId, Authentication authentication) { 
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
                if(!br.findById(basketId).isPresent()) 
                        throw new ItemNotFoundException("No Such Basket"); 
                br.deleteByIdAndUserID(basketId, userDetails.getId()); 
                System.out.println(">>>>>>>>>>>" + basketId); 
                return false;// It should be changed ! 
        } 
 
        @GetMapping(value = "/baskets/{basketId}") 
        public MappingJacksonValue getBasket(@PathVariable long basketId, Authentication authentication) { 
                System.out.println("+++"); 
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
                Basket basket = br.findByIdAndUserID(basketId, userDetails.getId()); 
                if(basket==null) 
                        throw new ItemNotFoundException("No Baskets"); 
                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "code", "name"); 
                FilterProvider filters = new SimpleFilterProvider().addFilter("FilterBasketProps", filter); 
                MappingJacksonValue mapping = new MappingJacksonValue(basket); 
                mapping.setFilters(filters); 
                return mapping; 
        } 
}