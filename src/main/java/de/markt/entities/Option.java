package de.markt.entities;
import javax.persistence.*; 

import org.springframework.hateoas.RepresentationModel; 
 
import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;
import java.util.List; 
 
@JsonFilter("FilterOptionProps") 
@Entity 
@Table(name="M_OPTION") 
public class Option extends RepresentationModel<Option>  implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        private String description; 
 
        private String name; 
 
        private boolean isEffectiveInPrice,isLOV; 
        @OneToMany(mappedBy="option") 
        private List<OptionItem> optionItems; 
         
        @ManyToOne 
        private OptionClassifications classification; 
         
        @ManyToOne 
        private Category category; 
 
        public Option() { 
        } 
 
        public OptionClassifications getClassification() { 
                return classification; 
        } 
 
        public void setClassification(OptionClassifications classification) { 
                this.classification = classification; 
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
 
        public String getName() { 
                return this.name; 
        } 
 
        public void setName(String name) { 
                this.name = name; 
        } 
 
        public boolean isEffectiveInPrice() { 
                return isEffectiveInPrice; 
        } 
 
        public void setEffectiveInPrice(boolean isEffectiveInPrice) { 
                this.isEffectiveInPrice = isEffectiveInPrice; 
        } 
 
        public boolean isLOV() { 
                return isLOV; 
        } 
 
        public void setLOV(boolean isLOV) { 
                this.isLOV = isLOV; 
        } 
 
        public Category getCategory() { 
                return category; 
        } 
 
        public void setCategory(Category category) { 
                this.category = category; 
        } 
 
        public List<OptionItem> getOptionItems() { 
                return optionItems; 
        } 
 
        public void setOptionItems(List<OptionItem> optionItems) { 
                this.optionItems = optionItems; 
        } 
}