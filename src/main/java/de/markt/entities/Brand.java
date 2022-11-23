package de.markt.entities;

import java.io.Serializable; 
import javax.persistence.*; 

import org.springframework.hateoas.RepresentationModel; 

import com.fasterxml.jackson.annotation.JsonFilter; 
import java.util.Set; 

/** 
 * The persistent class for the M_Brand database table. 
 *  
 */ 
@JsonFilter("FilterBrandProps") 
@Entity 
@Table(name = "M_Brand") 
//@NamedQuery(name="MBasket.findAll", query="SELECT m FROM MBasket m") 
public class Brand extends RepresentationModel<Brand> implements Serializable { 
        private static final long serialVersionUID = 1L; 
        @Id 
        @GeneratedValue(strategy = GenerationType.AUTO) 
        private long id; 

        private String name; 
         

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand") 
        private Set<CategoryBrand> categoryOfBrands; 

         
        public Brand() { 
        } 

        public long getId() { 
                return this.id; 
        } 

        public void setId(long id) { 
                this.id = id; 
        } 

        public String getName() { 
                return this.name; 
        } 

        public void setName(String name) { 
                this.name = name; 
        } 

        public Set<CategoryBrand> getCategoryOfBrands() { 
                return categoryOfBrands; 
        } 

        public void setCategoryOfBrands(Set<CategoryBrand> categoryOfBrands) { 
                this.categoryOfBrands = categoryOfBrands; 
        } 
}