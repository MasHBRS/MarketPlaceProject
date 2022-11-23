package de.markt.controllers;
import java.net.URI; 
import java.util.List; 
import java.util.Optional; 
 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.http.converter.json.MappingJacksonValue; 
import org.springframework.security.core.Authentication; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; 
 
import com.fasterxml.jackson.databind.ser.FilterProvider; 
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter; 
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider; 
 
import de.markt.controllers.errors.ItemNotFoundException; 
import de.markt.entities.Brand; 
import de.markt.entities.Category; 
import de.markt.entities.CategoryBrand; 
import de.markt.repositories.BrandRepository; 
import de.markt.repositories.CategoryBrandRepository; 
import de.markt.repositories.CategoryRepository; 
 
@RestController 
public class CategoryBrandController { 
        @Autowired 
        CategoryBrandRepository cbr; 
        @Autowired 
        CategoryRepository cr; 
        @Autowired 
        BrandRepository br; 
 
        @GetMapping(value = "/categoryBrand") 
        public MappingJacksonValue getCategories() { 
                List<CategoryBrand> list = cbr.findAll(); 
                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "category", 
                                "brand"); 
                FilterProvider filters = new SimpleFilterProvider().addFilter("FilterCategoryBrandProps", filter) 
                                .addFilter("FilterCategoryProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name")) 
                                .addFilter("FilterBrandProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name")); 
                MappingJacksonValue mapping = new MappingJacksonValue(list); 
                mapping.setFilters(filters); 
                return mapping; 
        } 
 
        @GetMapping(value = "/categories/{categoryID}/categoryBrand") // bayad az user beresim be basket 
        public MappingJacksonValue getCategory(Authentication authentication, @PathVariable long categoryID) { 
                Optional<Category> category = cr.findById(categoryID); 
                if (category.isPresent()) { 
                        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"); 
                        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterCategoryBrandProps", filter); 
                        MappingJacksonValue mapping = new MappingJacksonValue(category.get().getCategoryOfBrands()); 
                        mapping.setFilters(filters); 
                        return mapping; 
                } 
                throw new ItemNotFoundException("No Such Category"); 
        } 
 
        @GetMapping(value = "/brands/{brandId}/categoryBrand") 
        public MappingJacksonValue getCategoryOfBrand(@PathVariable long brandId) { 
                Optional<Brand> brand = br.findById(brandId); 
 
                if (brand.isPresent() && brand.get() != null) { 
                        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "category", 
                                        "brand"); 
                        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterCategoryBrandProps", filter) 
                                        .addFilter("FilterCategoryProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name")) 
                                        .addFilter("FilterBrandProps", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name")); 
                        MappingJacksonValue mapping = new MappingJacksonValue(brand.get().getCategoryOfBrands()); 
                        mapping.setFilters(filters); 
                        return mapping; 
                } 
                throw new ItemNotFoundException("No Such Brand"); 
        } 
 
        @PostMapping(value = "/categoryBrand") 
        public ResponseEntity<Object> addBasket(@RequestBody CategoryBrand cb, Authentication authentication) { 
                try { 
                        // UserDetailsImpl userDetails = (UserDetailsImpl) 
                        // authentication.getPrincipal(); 
                        CategoryBrand savedCategory = cbr.save(cb); 
                        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
                                        .buildAndExpand(savedCategory.getId()).toUri(); 
                        return ResponseEntity.created(location).build(); 
                } catch (Exception e) { 
                        e.printStackTrace(); 
                } 
                return null; 
        } 
 
        /* 
         *  
         * @GetMapping(value = "/categories/{categoryID}/products") public 
         * MappingJacksonValue getProductsOfCategory(@PathVariable long categoryID) { 
         * Optional<Category> category = cr.findById(categoryID); if 
         * (category.isPresent()) { List<Product>products= category.get().getProducts(); 
         * SimpleBeanPropertyFilter filter = 
         * SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", 
         * "parent","products"); FilterProvider filters = new 
         * SimpleFilterProvider().addFilter("FilterCategoryProps", filter) 
         * .addFilter("FilterProductProps", 
         * SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "description")); 
         * MappingJacksonValue mapping = new MappingJacksonValue(products); 
         * mapping.setFilters(filters); return mapping; } return null; } 
         *  
         * @DeleteMapping(value = "/categories/{categoryID}") public Boolean 
         * removeBasket(@PathVariable long categoryId, Authentication authentication) { 
         * UserDetailsImpl userDetails = (UserDetailsImpl) 
         * authentication.getPrincipal(); cr.deleteById(categoryId); return false;// It 
         * should be changed ! } 
         */ 
}