package de.markt.entities;
import java.io.Serializable;
import java.util.List; 

import javax.persistence.*; 
 
import org.springframework.hateoas.RepresentationModel; 
 
import com.fasterxml.jackson.annotation.JsonFilter; 
import com.fasterxml.jackson.annotation.JsonIgnore; 
 
/** 
 * The persistent class for the M_Brand database table. 
 *  
 */ 
@JsonFilter("FilterCategoryBrandProps") 
@Entity 
@Table(name = "M_CategoryBrand") 
//@NamedQuery(name="MBasket.findAll", query="SELECT m FROM MBasket m") 
public class CategoryBrand extends RepresentationModel<CategoryBrand>  implements Serializable { 
        private static final long serialVersionUID = 1L; 
        @Id 
        @GeneratedValue(strategy = GenerationType.AUTO) 
        private long id; 
 
        private String name; 
         
        @ManyToOne//(fetch = FetchType.LAZY) 
        @JoinColumn(name="Category_ID",nullable=false) 
        private Category category; 
         
        @ManyToOne//(fetch = FetchType.LAZY) 
        @JoinColumn(name="Brand_ID",nullable=true) 
        private Brand brand; 
         
        @OneToMany(mappedBy = "categoryBrand") 
        private List<Product> products; 
         
        public CategoryBrand() { 
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
 
        public Category getCategory() { 
                return category; 
        } 
 
        public void setCategory(Category category) { 
                this.category = category; 
        } 
 
        public Brand getBrand() { 
                return brand; 
        } 
 
        public void setBrand(Brand brand) { 
                this.brand = brand; 
        } 
}