package de.markt.entities;
import javax.persistence.*; 
 
import org.springframework.hateoas.RepresentationModel; 
 
import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;
import java.util.List; 
 
 
/** 
 * The persistent class for the M_OPTION database table. 
 *  
 */ 
@JsonFilter("FilterOptionClassificationProps") 
@Entity 
@Table(name="M_Classification_Of_OPTIONS") 
//@NamedQuery(name="MOption.findAll", query="SELECT m FROM MOption m") 
public class OptionClassifications extends RepresentationModel<OptionClassifications> implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        private String description; 
 
        private String name; 
 
        @OneToMany(mappedBy="classification") 
        private List<Option> options; 
         
        public OptionClassifications() { 
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
 
        public List<Option> getOptions() { 
                return options; 
        } 
 
        public void setOptions(List<Option> options) { 
                this.options = options; 
        } 
}