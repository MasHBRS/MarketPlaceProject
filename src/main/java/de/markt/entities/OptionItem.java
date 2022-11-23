package de.markt.entities;
import java.io.Serializable;

import javax.persistence.*; 
 
import org.springframework.hateoas.RepresentationModel; 
 
import com.fasterxml.jackson.annotation.JsonFilter; 
 
/** 
 * The persistent class for the M_OPTION database table. 
 *  
 */ 
@JsonFilter("FilterOptionItemProps") 
@Entity 
@Table(name="M_OPTION_ITEM") 
//@NamedQuery(name="MOption.findAll", query="SELECT m FROM MOption m") 
public class OptionItem  extends RepresentationModel<OptionItem> implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        private String description; 
 
        private String value; 
 
        @ManyToOne 
        private Option option; 
         
        @ManyToOne 
        private Product product; 
         
        public OptionItem() { 
        } 
 
        public long getId() { 
                return this.id; 
        } 
 
        public void setId(long id) { 
                this.id = id; 
        } 
 
        public String getDescription() { 
                return this.description; 
        } 
 
        public void setDescription(String description) { 
                this.description = description; 
        } 
 
        public String getValue() { 
                return value; 
        } 
 
        public void setValue(String value) { 
                this.value = value; 
        } 
 
        public Option getOption() { 
                return option; 
        } 
 
        public void setOption(Option option) { 
                this.option = option; 
        } 
 
        public Product getProduct() { 
                return product; 
        } 
 
        public void setProduct(Product product) { 
                this.product = product; 
        } 
}